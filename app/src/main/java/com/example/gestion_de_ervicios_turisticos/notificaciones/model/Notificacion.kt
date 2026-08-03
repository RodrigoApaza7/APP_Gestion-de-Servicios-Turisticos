package com.example.gestion_de_ervicios_turisticos.notificaciones.model

data class Notificacion(
    val id: String,
    val titulo: String,
    val mensaje: String,
    val fecha: String,
    val tipo: TipoNotificacion,
    val leida: Boolean = false
)

enum class TipoNotificacion {
    RESERVA, PROMOCION, SISTEMA
}