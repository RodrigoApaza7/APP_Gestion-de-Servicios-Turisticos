package com.example.gestion_de_ervicios_turisticos.auth.model

data class User(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val rol: String = "turista",
    val telefono: String = "",
    val fechaNacimiento: String = "",
    val nombreNegocio: String? = null,
    val ruc: String? = null
)

enum class RolUsuario(val valor: String, val etiqueta: String) {
    TURISTA("turista", "Turista"),
    PRESTADOR("prestador", "Prestador de servicios")
}