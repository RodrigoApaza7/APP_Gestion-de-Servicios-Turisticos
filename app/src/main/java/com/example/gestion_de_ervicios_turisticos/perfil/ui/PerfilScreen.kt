package com.example.gestion_de_ervicios_turisticos.perfil.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario

@Composable
fun PerfilScreen(
    onIrAHistorial: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val usuario = SesionUsuario.usuarioActual.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Encabezado con avatar, nombre y correo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                // TODO: reemplazar por la foto real del usuario cuando exista esa función
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Foto de perfil",
                    tint = Color(0xFF9E9E9E),
                    modifier = Modifier.size(56.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = usuario?.nombre ?: "Invitado",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = usuario?.correo ?: "No has iniciado sesión",
                fontSize = 14.sp,
                color = Color(0xFF757575)
            )

            if (usuario != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF0F4C46).copy(alpha = 0.1f))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = usuario.rol.replaceFirstChar { it.uppercase() },
                        fontSize = 12.sp,
                        color = Color(0xFF0F4C46),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        HorizontalDivider(color = Color(0xFFEEEEEE))

        Spacer(modifier = Modifier.height(8.dp))

        // Opciones del menú de perfil
        OpcionPerfil(
            icono = Icons.Filled.History,
            texto = "Historial de reservas",
            onClick = onIrAHistorial
        )
        OpcionPerfil(
            icono = Icons.Filled.Edit,
            texto = "Editar perfil",
            onClick = { /* TODO: pantalla de edición, si da tiempo */ }
        )
        OpcionPerfil(
            icono = Icons.Filled.Settings,
            texto = "Configuración",
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.weight(1f))

        OpcionPerfil(
            icono = Icons.Filled.Logout,
            texto = "Cerrar sesión",
            colorTexto = Color.Red,
            onClick = {
                SesionUsuario.cerrarSesion()
                onCerrarSesion()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun OpcionPerfil(
    icono: ImageVector,
    texto: String,
    colorTexto: Color = Color.Black,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icono, contentDescription = null, tint = colorTexto)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = texto, fontSize = 15.sp, color = colorTexto)
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    // Simula un usuario con sesión iniciada, solo para ver el Preview con datos
    PerfilScreen(
        onIrAHistorial = {},
        onCerrarSesion = {}
    )
}