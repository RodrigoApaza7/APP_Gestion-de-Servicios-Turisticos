package com.example.gestion_de_ervicios_turisticos.auth.model

data class User(
    val id: String,
    val nombre: String,
    val correo: String,
    val rol: String = "turista"
)