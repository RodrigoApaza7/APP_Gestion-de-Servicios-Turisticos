package com.example.gestion_de_ervicios_turisticos.itinerario.model.request

data class CrearDetalleItinerarioRequest(
    val idServicio: Int,
    val fecha: String? = null,
    val hora: String? = null,
    val orden: Short? = null,
    val notas: String? = null
)