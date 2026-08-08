package com.example.gestion_de_ervicios_turisticos.itinerario.model.response

data class ItinerarioResponse(
    val idItinerario: Int,
    val idUsuario: Int,
    val nombre: String,
    val descripcion: String?,
    val imagenPortada: String?,
    val fechaInicio: String?,
    val fechaFin: String?,
    val compartido: Boolean?,
    val publico: Boolean?,
    val fechaCreacion: String?,
    val fechaActualizacion: String?
)