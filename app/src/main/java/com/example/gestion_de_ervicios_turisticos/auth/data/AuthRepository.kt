package com.example.gestion_de_ervicios_turisticos.auth.data

import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val usuariosCollection = db.collection("usuarios")

    suspend fun login(correo: String, contrasena: String): Result<User> {
        return try {
            val resultado = auth.signInWithEmailAndPassword(correo, contrasena).await()
            val uid = resultado.user?.uid
                ?: return Result.failure(Exception("No se pudo obtener el usuario"))
            val usuario = obtenerUsuarioDesdeFirestore(uid)
                ?: return Result.failure(Exception("No se encontraron datos de este usuario"))
            Result.success(usuario)
        } catch (e: Exception) {
            Result.failure(Exception(traducirErrorFirebase(e)))
        }
    }

    suspend fun registrar(
        nombre: String,
        correo: String,
        contrasena: String,
        telefono: String,
        fechaNacimiento: String,
        rol: String,
        nombreNegocio: String? = null,
        ruc: String? = null
    ): Result<User> {
        return try {
            val resultado = auth.createUserWithEmailAndPassword(correo, contrasena).await()
            val uid = resultado.user?.uid
                ?: return Result.failure(Exception("No se pudo crear el usuario"))

            val nuevoUsuario = User(
                id = uid,
                nombre = nombre,
                correo = correo,
                rol = rol,
                telefono = telefono,
                fechaNacimiento = fechaNacimiento,
                nombreNegocio = nombreNegocio,
                ruc = ruc
            )
            usuariosCollection.document(uid).set(nuevoUsuario).await()
            Result.success(nuevoUsuario)
        } catch (e: Exception) {
            Result.failure(Exception(traducirErrorFirebase(e)))
        }
    }
    suspend fun iniciarSesionConGoogle(idToken: String): Result<User> {
        return try {
            val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
            val resultado = auth.signInWithCredential(credential).await()
            val uid = resultado.user?.uid
                ?: return Result.failure(Exception("No se pudo obtener el usuario"))

            // Si es la primera vez que entra con Google, puede que no exista en Firestore todavía
            var usuario = obtenerUsuarioDesdeFirestore(uid)
            if (usuario == null) {
                usuario = User(
                    id = uid,
                    nombre = resultado.user?.displayName ?: "Usuario",
                    correo = resultado.user?.email ?: "",
                    rol = "turista" // por defecto; si quieres, luego le pedimos elegir rol después del primer login
                )
                usuariosCollection.document(uid).set(usuario).await()
            }
            Result.success(usuario)
        } catch (e: Exception) {
            Result.failure(Exception(traducirErrorFirebase(e)))
        }
    }
    suspend fun actualizarUsuario(usuario: User): Result<User> {
        return try {
            usuariosCollection.document(usuario.id).set(usuario).await()
            Result.success(usuario)
        } catch (e: Exception) {
            Result.failure(Exception(traducirErrorFirebase(e)))
        }
    }

    fun cerrarSesionFirebase() {
        auth.signOut()
    }

    private suspend fun obtenerUsuarioDesdeFirestore(uid: String): User? {
        val snapshot = usuariosCollection.document(uid).get().await()
        return snapshot.toObject(User::class.java)
    }

    private fun traducirErrorFirebase(e: Exception): String {
        val mensaje = e.message ?: return "Ocurrió un error inesperado"
        return when {
            mensaje.contains("password is invalid") -> "Correo o contraseña incorrectos"
            mensaje.contains("no user record") -> "No existe una cuenta con ese correo"
            mensaje.contains("already in use") -> "Ya existe una cuenta con ese correo"
            mensaje.contains("badly formatted") -> "El correo no tiene un formato válido"
            mensaje.contains("network error") -> "Sin conexión a internet"
            mensaje.contains("weak password") -> "La contraseña es muy débil (mínimo 6 caracteres)"
            else -> mensaje
        }
    }
}