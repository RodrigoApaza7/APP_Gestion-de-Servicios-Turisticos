package com.example.gestion_de_ervicios_turisticos.notificaciones.data

import androidx.compose.runtime.mutableStateListOf
import com.example.gestion_de_ervicios_turisticos.notificaciones.model.Notificacion
import com.example.gestion_de_ervicios_turisticos.notificaciones.model.TipoNotificacion

object NotificacionRepository {

    val notificaciones = mutableStateListOf(
        Notificacion("n1", "Reserva confirmada", "Tu reserva en Hotel Titikaka fue confirmada.", "Hace 2 horas", TipoNotificacion.RESERVA),
        Notificacion("n2", "Nueva promoción", "20% de descuento en tours por el Valle Sagrado, Cusco.", "Hace 1 día", TipoNotificacion.PROMOCION),
        Notificacion("n3", "Recordatorio", "Tu tour a las Islas Uros es mañana a las 8:00 AM.", "Hace 1 día", TipoNotificacion.RESERVA, leida = true),
        Notificacion("n4", "Bienvenido a Saranta", "Explora los mejores destinos turísticos de Perú.", "Hace 3 días", TipoNotificacion.SISTEMA, leida = true)
    )

    fun marcarComoLeida(id: String) {
        val index = notificaciones.indexOfFirst { it.id == id }
        if (index != -1) {
            notificaciones[index] = notificaciones[index].copy(leida = true)
        }
    }

    fun hayNoLeidas(): Boolean = notificaciones.any { !it.leida }
}