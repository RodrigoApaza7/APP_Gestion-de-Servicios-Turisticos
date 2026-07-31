package com.example.gestion_de_ervicios_turisticos.reservas.model

data class Reserva(
    val id: String,
    val nombreServicio: String,
    val destino: String,
    val fecha: String, // formato simple por ahora, ej: "15 Ago 2026"
    val precio: Double,
    val estado: EstadoReserva
)

enum class EstadoReserva(val etiqueta: String) {
    TODAS("Todas"),
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada")
}