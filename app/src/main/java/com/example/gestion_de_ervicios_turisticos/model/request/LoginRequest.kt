package com.example.gestion_de_ervicios_turisticos.model.request

data class LoginRequest(
    val correo: String,
    val password: String
)