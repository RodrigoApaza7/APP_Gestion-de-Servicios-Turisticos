package com.example.gestion_de_ervicios_turisticos.home.model

data class Servicio(
    val id: String,
    val nombre: String,
    val tipo: TipoServicio,
    val destino: String,
    val precio: Double,
    val calificacion: Double,
    val esDestacado: Boolean = false,
<<<<<<< HEAD
    val descripcion: String = "Descripción no disponible por el momento.", // valor por defecto, no rompe tus datos existentes
    val latitud: Double = 0.0,   // NUEVO
    val longitud: Double = 0.0   // NUEVO
=======
    val descripcion: String = "Descripción no disponible por el momento.",
    val latitud: Double = 0.0,
    val longitud: Double = 0.0,
    val prestadorId: String? = null // null = servicio "genérico" del catálogo demo; con id = pertenece a ese Prestador
>>>>>>> origin/main
)

enum class TipoServicio(val etiqueta: String) {
    TODOS("Todos"),
    HOTEL("Hoteles"),
    RESTAURANTE("Restaurantes"),
    TOUR("Tours"),
    TRANSPORTE("Transporte")
}