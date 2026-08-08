package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta
import kotlinx.coroutines.launch

@Composable
fun EditarPerfilScreen(
    onVolver: () -> Unit,
    onGuardadoExitoso: () -> Unit
) {
    val usuario = SesionUsuario.usuarioActual.value

    if (usuario == null) {
        onVolver()
        return
    }

    var nombre by remember { mutableStateOf(usuario.nombre) }
    var telefono by remember { mutableStateOf(usuario.telefono) }
    var fechaNacimiento by remember { mutableStateOf(usuario.fechaNacimiento) }
    var nombreNegocio by remember { mutableStateOf(usuario.nombreNegocio ?: "") }
    var ruc by remember { mutableStateOf(usuario.ruc ?: "") }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header con degradado, mismo estilo que Perfil e Historial
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(ColoresSaranta.VerdeOscuro, ColoresSaranta.AzulOscuro)
                    )
                )
                .padding(top = 48.dp, bottom = 20.dp, start = 8.dp, end = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Text("Editar perfil", fontSize = 19.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(ColoresSaranta.Crema),
                contentAlignment = Alignment.Center
            ) {
                // TODO: reemplazar por selector de foto real
                Icon(Icons.Filled.Person, contentDescription = null, tint = ColoresSaranta.AzulOscuro, modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            CampoEdicion(valor = nombre, onCambio = { nombre = it }, etiqueta = "Nombre completo")
            Spacer(modifier = Modifier.height(12.dp))

            // Correo: NO editable (es el identificador de la cuenta, cambiarlo requeriría re-verificación)
            CampoEdicion(valor = usuario.correo, onCambio = {}, etiqueta = "Correo (no editable)", habilitado = false)
            Spacer(modifier = Modifier.height(12.dp))

            CampoEdicion(valor = telefono, onCambio = { telefono = it }, etiqueta = "Teléfono")
            Spacer(modifier = Modifier.height(12.dp))

            CampoEdicion(valor = fechaNacimiento, onCambio = { fechaNacimiento = it }, etiqueta = "Fecha de nacimiento")
            Spacer(modifier = Modifier.height(12.dp))

            if (usuario.rol == "prestador") {
                CampoEdicion(valor = nombreNegocio, onCambio = { nombreNegocio = it }, etiqueta = "Nombre del negocio")
                Spacer(modifier = Modifier.height(12.dp))
                CampoEdicion(valor = ruc, onCambio = { ruc = it }, etiqueta = "RUC")
                Spacer(modifier = Modifier.height(12.dp))
            }

            error?.let {
                Text(it, color = Color(0xFFC62828), fontSize = 13.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (nombre.isBlank() || telefono.isBlank()) {
                        error = "El nombre y teléfono no pueden estar vacíos"
                        return@Button
                    }
                    val usuarioActualizado = usuario.copy(
                        nombre = nombre,
                        telefono = telefono,
                        fechaNacimiento = fechaNacimiento,
                        nombreNegocio = if (usuario.rol == "prestador") nombreNegocio else null,
                        ruc = if (usuario.rol == "prestador") ruc else null
                    )
                    scope.launch {
                        AuthRepository.actualizarUsuario(usuarioActualizado)
                        SesionUsuario.actualizarUsuario(usuarioActualizado)
                        onGuardadoExitoso()
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Guardar cambios", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun CampoEdicion(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: String,
    habilitado: Boolean = true
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        enabled = habilitado,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColoresSaranta.VerdeOscuro,
            focusedLabelColor = ColoresSaranta.VerdeOscuro
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilScreenPreview() {
    // Simula un usuario con sesión iniciada, solo para que el Preview tenga datos que mostrar
    SesionUsuario.iniciarSesion(
        User(
            id = "1",
            nombre = "Usuario Demo",
            correo = "demo@travelhub.com",
            rol = "turista",
            telefono = "987654321",
            fechaNacimiento = "15/03/2000"
        )
    )
    EditarPerfilScreen(onVolver = {}, onGuardadoExitoso = {})
}