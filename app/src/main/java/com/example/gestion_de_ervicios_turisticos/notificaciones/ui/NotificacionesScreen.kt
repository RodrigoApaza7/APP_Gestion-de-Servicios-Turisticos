package com.example.gestion_de_ervicios_turisticos.notificaciones.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
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
import com.example.gestion_de_ervicios_turisticos.notificaciones.data.NotificacionRepository
import com.example.gestion_de_ervicios_turisticos.notificaciones.model.Notificacion
import com.example.gestion_de_ervicios_turisticos.notificaciones.model.TipoNotificacion
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun NotificacionesScreen(
    onVolver: () -> Unit
) {
    val notificaciones = NotificacionRepository.notificaciones

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header con degradado, mismo estilo que Perfil/Historial
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
                Text("Notificaciones", fontSize = 19.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }

        if (notificaciones.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No tienes notificaciones", color = ColoresSaranta.Negro.copy(alpha = 0.5f))
            }
        } else {
            LazyColumnNotificaciones(notificaciones)
        }
    }
}

@Composable
private fun LazyColumnNotificaciones(notificaciones: List<Notificacion>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(notificaciones) { notificacion ->
            TarjetaNotificacion(
                notificacion = notificacion,
                onClick = { NotificacionRepository.marcarComoLeida(notificacion.id) }
            )
        }
    }
}

@Composable
private fun TarjetaNotificacion(notificacion: Notificacion, onClick: () -> Unit) {
    val (icono, colorIcono) = when (notificacion.tipo) {
        TipoNotificacion.RESERVA -> Icons.Filled.CalendarMonth to ColoresSaranta.VerdeOscuro
        TipoNotificacion.PROMOCION -> Icons.Filled.LocalOffer to ColoresSaranta.Dorado
        TipoNotificacion.SISTEMA -> Icons.Filled.Info to ColoresSaranta.AzulOscuro
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(if (notificacion.leida) Color.White else ColoresSaranta.Crema.copy(alpha = 0.5f))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(colorIcono.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icono, contentDescription = null, tint = colorIcono, modifier = Modifier.size(22.dp))
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                notificacion.titulo,
                fontWeight = if (notificacion.leida) FontWeight.Normal else FontWeight.Bold,
                fontSize = 14.sp,
                color = ColoresSaranta.Negro
            )
            Text(notificacion.mensaje, fontSize = 12.sp, color = ColoresSaranta.Negro.copy(alpha = 0.6f))
            Spacer(modifier = Modifier.height(4.dp))
            Text(notificacion.fecha, fontSize = 11.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.6f))
        }

        if (!notificacion.leida) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(ColoresSaranta.Dorado)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacionesScreenPreview() {
    NotificacionesScreen(onVolver = {})
}