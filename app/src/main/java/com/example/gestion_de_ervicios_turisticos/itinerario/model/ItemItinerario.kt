package com.example.gestion_de_ervicios_turisticos.itinerario.model

data class ItemItinerario(
    val servicioId: String,
    val nombreServicio: String,
    val destino: String,
    val precio: Double,
    val latitud: Double = 0.0,   // NUEVO
    val longitud: Double = 0.0  // NUEVO
)