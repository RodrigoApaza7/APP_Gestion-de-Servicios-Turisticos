package com.example.gestion_de_ervicios_turisticos.auth.data

import com.example.gestion_de_ervicios_turisticos.auth.model.User
import kotlinx.coroutines.delay

class AuthRepository {

    // Simula una "base de datos" de usuarios ya registrados
    private val usuariosRegistrados = mutableListOf(
        User(id = "1", nombre = "Usuario Demo", correo = "demo@travelhub.com")
    )
    private val credenciales = mutableMapOf("demo@travelhub.com" to "123456")


    suspend fun login(correo: String, contrasena: String): Result<User> {
        delay(1200) // simula tiempo de respuesta de una API real
        val contrasenaGuardada = credenciales[correo]
        return if (contrasenaGuardada != null && contrasenaGuardada == contrasena) {
            val usuario = usuariosRegistrados.first { it.correo == correo }
            Result.success(usuario)
        } else {
            Result.failure(Exception("Correo o contraseña incorrectos"))
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
        delay(1200)
        if (credenciales.containsKey(correo)) {
            return Result.failure(Exception("Ya existe una cuenta con ese correo"))
        }
        val nuevoUsuario = User(
            id = (usuariosRegistrados.size + 1).toString(),
            nombre = nombre,
            correo = correo,
            rol = rol,
            telefono = telefono,
            fechaNacimiento = fechaNacimiento,
            nombreNegocio = nombreNegocio,
            ruc = ruc
        )
        usuariosRegistrados.add(nuevoUsuario)
        credenciales[correo] = contrasena
        return Result.success(nuevoUsuario)
    }
    suspend fun loginConGoogle(correo: String, nombre: String, idToken: String): Result<User> {
        delay(1200) // simula tiempo de respuesta de una API real
        var usuario = usuariosRegistrados.find { it.correo == correo }
        if (usuario == null) {
            usuario = User(
                id = (usuariosRegistrados.size + 1).toString(),
                nombre = nombre,
                correo = correo,
                rol = "turista"
            )
            usuariosRegistrados.add(usuario)
        }
        return Result.success(usuario)
    }

    fun actualizarUsuario(usuarioActualizado: User) {
        val index = usuariosRegistrados.indexOfFirst { it.id == usuarioActualizado.id }
        if (index != -1) {
            usuariosRegistrados[index] = usuarioActualizado
        }
    }
}