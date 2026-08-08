package com.example.gestion_de_ervicios_turisticos.model.response

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData?,
    val errors: List<String>,
    val timestamp: String
)

data class LoginData(
    val idUsuario: Int,
    val nombre: String,
    val correo: String,
    val idRol: Int,
    val token: String
)