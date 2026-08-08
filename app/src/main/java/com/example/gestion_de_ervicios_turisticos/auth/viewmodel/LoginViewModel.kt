package com.example.gestion_de_ervicios_turisticos.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.auth.data.AuthRepository
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Inactivo)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun iniciarSesion(correo: String, contrasena: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando

            val resultado = repository.login(correo, contrasena)

            _uiState.value = resultado.fold(
                onSuccess = {
                    AuthUiState.Exito(
                        usuario = it.usuario,
                        token = it.token
                    )
                },
                onFailure = {
                    AuthUiState.Error(
                        it.message ?: "Error desconocido"
                    )
                }
            )
        }
    }

    fun iniciarSesionConGoogle(credential: GoogleIdTokenCredential) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Cargando
            val resultado = repository.loginConGoogle(
                correo = credential.id,
                nombre = credential.displayName ?: "Usuario Google",
                idToken = credential.idToken
            )
            _uiState.value = resultado.fold(
                onSuccess = {
                    AuthUiState.Exito(
                        usuario = it,
                        token = ""
                    )
                },
                onFailure = { AuthUiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}