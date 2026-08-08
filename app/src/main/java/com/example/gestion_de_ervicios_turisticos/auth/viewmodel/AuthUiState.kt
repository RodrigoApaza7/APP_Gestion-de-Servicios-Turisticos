package com.example.gestion_de_ervicios_turisticos.auth.viewmodel

import com.example.gestion_de_ervicios_turisticos.auth.model.User

sealed class AuthUiState {
    object Inactivo : AuthUiState()
    object Cargando : AuthUiState()
    data class Exito(
        val usuario: User,
        val token: String
    ) : AuthUiState()
    data class Error(val mensaje: String) : AuthUiState()
}