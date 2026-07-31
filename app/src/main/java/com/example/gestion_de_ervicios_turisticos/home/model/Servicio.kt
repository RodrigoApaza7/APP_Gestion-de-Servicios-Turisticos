package com.example.gestion_de_ervicios_turisticos.home.model

data class Servicio(
    val id: String,
    val nombre: String,
    val tipo: TipoServicio,
    val destino: String,
    val precio: Double,
    val calificacion: Double,
    val esDestacado: Boolean = false,
    val descripcion: String = "Descripción no disponible por el momento." // valor por defecto, no rompe tus datos existentes
)

enum class TipoServicio(val etiqueta: String) {
    TODOS("Todos"),
    HOTEL("Hoteles"),
    RESTAURANTE("Restaurantes"),
    TOUR("Tours"),
    TRANSPORTE("Transporte")
}