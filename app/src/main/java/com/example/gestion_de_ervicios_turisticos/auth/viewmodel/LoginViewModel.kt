package com.example.gestion_de_ervicios_turisticos.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Inactivo)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun iniciarSesion(correo: String, contrasena: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando
            val resultado = AuthRepository.login(correo, contrasena)
            _uiState.value = resultado.fold(
                onSuccess = { AuthUiState.Exito(it) },
                onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
    fun iniciarSesionConGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando
            val resultado = AuthRepository.iniciarSesionConGoogle(idToken)
            _uiState.value = resultado.fold(
                onSuccess = { AuthUiState.Exito(it) },
                onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}