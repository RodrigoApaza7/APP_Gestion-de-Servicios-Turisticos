package com.example.gestion_de_ervicios_turisticos.itinerario.model.request

data class CrearItinerarioRequest(
    val idUsuario: Int,
    val nombre: String,
    val descripcion: String? = null,
    val imagenPortada: String? = null,
    val fechaInicio: String? = null,
    val fechaFin: String? = null,
    val compartido: Boolean? = false,
    val publico: Boolean? = false
)