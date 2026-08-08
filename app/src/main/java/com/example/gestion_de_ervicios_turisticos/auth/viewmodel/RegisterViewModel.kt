package com.example.gestion_de_ervicios_turisticos.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Inactivo)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun registrar(
        nombre: String,
        correo: String,
        contrasena: String,
        telefono: String,
        fechaNacimiento: String,
        rol: String,
        nombreNegocio: String? = null,
        ruc: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando
            val resultado = AuthRepository.registrar(
                nombre, correo, contrasena, telefono, fechaNacimiento, rol, nombreNegocio, ruc
            )
            _uiState.value = resultado.fold(
                onSuccess = { AuthUiState.Exito(it) },
                onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}