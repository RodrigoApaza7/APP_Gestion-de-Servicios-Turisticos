package com.example.gestion_de_ervicios_turisticos.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Inactivo)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun registrar(nombre: String, correo: String, contrasena: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando
            val resultado = repository.registrar(nombre, correo, contrasena)
            _uiState.value = resultado.fold(
                onSuccess = { AuthUiState.Exito(it) },
                onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}