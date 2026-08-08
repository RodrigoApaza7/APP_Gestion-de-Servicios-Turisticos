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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta


// Paleta de la app, centralizada aquí para reutilizarla fácil


@Composable
fun PerfilScreen(
    onIrAHistorial: () -> Unit,
    onIrAEditarPerfil: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val usuario = SesionUsuario.usuarioActual.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header con degradado de marca
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(ColoresSaranta.VerdeOscuro, ColoresSaranta.AzulOscuro)
                    )
                )
                .padding(top = 48.dp, bottom = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    // TODO: reemplazar por la foto real del usuario cuando exista esa función
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Foto de perfil",
                        tint = Color.White,
                        modifier = Modifier.size(56.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = usuario?.nombre ?: "Invitado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = usuario?.correo ?: "No has iniciado sesión",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                if (usuario != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(ColoresSaranta.Dorado)
                            .padding(horizontal = 14.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = usuario.rol.replaceFirstChar { it.uppercase() },
                            fontSize = 12.sp,
                            color = ColoresSaranta.Negro,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Tarjeta con datos personales, superpuesta al header (efecto "card" flotante)
        if (usuario != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-20).dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(ColoresSaranta.Crema)
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            ) {
                InfoFila(etiqueta = "Teléfono", valor = usuario.telefono.ifBlank { "No registrado" })
                InfoFila(etiqueta = "Fecha de nacimiento", valor = usuario.fechaNacimiento.ifBlank { "No registrada" })

                if (usuario.rol == "prestador") {
                    InfoFila(etiqueta = "Negocio", valor = usuario.nombreNegocio ?: "No registrado")
                    InfoFila(etiqueta = "RUC", valor = usuario.ruc ?: "No registrado")
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Opciones del menú de perfil
        OpcionPerfil(
            icono = Icons.Filled.History,
            texto = "Historial de reservas",
            onClick = onIrAHistorial
        )
        OpcionPerfil(
            icono = Icons.Filled.Edit,
            texto = "Editar perfil",
            onClick = onIrAEditarPerfil
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
            colorIcono = Color(0xFFC62828),
            colorTexto = Color(0xFFC62828),
            onClick = {
                AuthRepository.cerrarSesionFirebase()
                SesionUsuario.cerrarSesion()
                onCerrarSesion()
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun InfoFila(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(etiqueta, fontSize = 13.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
        Text(valor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ColoresSaranta.Negro)
    }
}

@Composable
private fun OpcionPerfil(
    icono: ImageVector,
    texto: String,
    colorIcono: Color = ColoresSaranta.Verde,
    colorTexto: Color = ColoresSaranta.Negro,
    onClick: () -> Unit          // <- así debe quedar, NO "onClick = onIrAEditarPerfil"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icono, contentDescription = null, tint = colorIcono)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = texto, fontSize = 15.sp, color = colorTexto)
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    PerfilScreen(
        onIrAHistorial = {},
        onIrAEditarPerfil = {},   // <- agrega esta línea
        onCerrarSesion = {}
    )
}