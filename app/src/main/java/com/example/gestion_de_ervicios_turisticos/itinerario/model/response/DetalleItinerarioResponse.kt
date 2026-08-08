package com.example.gestion_de_ervicios_turisticos.itinerario.model.response

data class DetalleItinerarioResponse(
    val idDetalle: Int,
    val idItinerario: Int,
    val idServicio: Int,
    val fecha: String?,
    val hora: String?,
    val orden: Short?,
    val notas: String?
)