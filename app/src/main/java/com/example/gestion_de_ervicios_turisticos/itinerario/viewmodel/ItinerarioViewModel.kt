package com.example.gestion_de_ervicios_turisticos.itinerario.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.itinerario.data.ItinerarioRepository
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearDetalleItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.DetalleItinerarioResponse
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.ItinerarioResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import android.util.Log

sealed class ItinerarioUiState {
    data object Inactivo : ItinerarioUiState()
    data object Cargando : ItinerarioUiState()
    data class Exito(val itinerarios: List<ItinerarioResponse>) : ItinerarioUiState()
    data class Error(val mensaje: String) : ItinerarioUiState()
}

sealed class DetallesUiState {
    data object Inactivo : DetallesUiState()
    data object Cargando : DetallesUiState()
    data class Exito(val detalles: List<DetalleItinerarioResponse>) : DetallesUiState()
    data class Error(val mensaje: String) : DetallesUiState()
}

class ItinerarioViewModel(
    private val repository: ItinerarioRepository = ItinerarioRepository()
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ItinerarioUiState>(ItinerarioUiState.Inactivo)

    val uiState: StateFlow<ItinerarioUiState> =
        _uiState.asStateFlow()

    private val _detallesState =
        MutableStateFlow<DetallesUiState>(DetallesUiState.Inactivo)

    val detallesState: StateFlow<DetallesUiState> =
        _detallesState.asStateFlow()


    // =========================
    // ITINERARIOS
    // =========================

    fun obtenerItinerarios(idUsuario: Int) {

        viewModelScope.launch {

            Log.d(
                "ITINERARIO_TEST",
                "=== obtenerItinerarios($idUsuario) ==="
            )

            _uiState.value = ItinerarioUiState.Cargando

            val resultado =
                repository.obtenerItinerariosPorUsuario(idUsuario)

            Log.d(
                "ITINERARIO_TEST",
                "Resultado recibido: $resultado"
            )

            _uiState.value = resultado.fold(

                onSuccess = {
                    Log.d(
                        "ITINERARIO_TEST",
                        "ÉXITO: ${it.size} itinerarios recibidos"
                    )

                    ItinerarioUiState.Exito(it)
                },

                onFailure = {
                    Log.e(
                        "ITINERARIO_TEST",
                        "ERROR: ${it.message}",
                        it
                    )

                    ItinerarioUiState.Error(
                        it.message ?: "Error al obtener itinerarios"
                    )
                }
            )
        }
    }

    fun crearItinerario(
        idUsuario: Int,
        nombre: String,
        descripcion: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value = ItinerarioUiState.Cargando

            val request = CrearItinerarioRequest(
                idUsuario = idUsuario,
                nombre = nombre,
                descripcion = descripcion
            )

            val resultado =
                repository.crearItinerario(request)

            resultado.onSuccess {
                obtenerItinerarios(idUsuario)
            }.onFailure {
                _uiState.value = ItinerarioUiState.Error(
                    it.message ?: "Error al crear itinerario"
                )
            }
        }
    }


    // =========================
    // DETALLES
    // =========================

    fun obtenerDetalles(idItinerario: Int) {
        viewModelScope.launch {
            _detallesState.value = DetallesUiState.Cargando

            val resultado =
                repository.obtenerDetalles(idItinerario)

            _detallesState.value = resultado.fold(
                onSuccess = {
                    DetallesUiState.Exito(it)
                },
                onFailure = {
                    DetallesUiState.Error(
                        it.message ?: "Error al obtener detalles"
                    )
                }
            )
        }
    }

    fun agregarServicio(
        idItinerario: Int,
        idServicio: Int,
        fecha: String? = null,
        hora: String? = null,
        orden: Short? = null,
        notas: String? = null
    ) {
        viewModelScope.launch {

            val request = CrearDetalleItinerarioRequest(
                idServicio = idServicio,
                fecha = fecha,
                hora = hora,
                orden = orden,
                notas = notas
            )

            val resultado =
                repository.crearDetalle(
                    idItinerario,
                    request
                )

            resultado.onSuccess {
                obtenerDetalles(idItinerario)
            }
        }
    }

    fun eliminarDetalle(
        idDetalle: Int,
        idItinerario: Int
    ) {
        viewModelScope.launch {

            val resultado =
                repository.eliminarDetalle(idDetalle)

            resultado.onSuccess {
                obtenerDetalles(idItinerario)
            }
        }
    }

    fun cargarSesionActual() {
        val usuario = SesionUsuario.usuarioActual.value

        if (usuario == null) {
            _uiState.value = ItinerarioUiState.Error(
                "No hay una sesión iniciada"
            )
            return
        }

        val idUsuario = usuario.id.toIntOrNull()

        if (idUsuario == null) {
            _uiState.value = ItinerarioUiState.Error(
                "El ID del usuario no es válido"
            )
            return
        }

        obtenerItinerarios(idUsuario)
    }

    fun cargarItinerariosDeSesion() {

        Log.d("ITINERARIO_TEST", "=== cargarItinerariosDeSesion() INICIADA ===")

        val usuario = SesionUsuario.usuarioActual.value

        Log.d(
            "ITINERARIO_TEST",
            "Usuario actual: $usuario"
        )

        if (usuario == null) {
            Log.e(
                "ITINERARIO_TEST",
                "NO HAY USUARIO EN SesionUsuario"
            )

            _uiState.value = ItinerarioUiState.Error(
                "No hay una sesión iniciada"
            )
            return
        }

        val idUsuario = usuario.id.toIntOrNull()

        Log.d(
            "ITINERARIO_TEST",
            "ID del usuario: ${usuario.id} -> convertido: $idUsuario"
        )

        if (idUsuario == null) {
            Log.e(
                "ITINERARIO_TEST",
                "EL ID DEL USUARIO NO ES VÁLIDO"
            )

            _uiState.value = ItinerarioUiState.Error(
                "El ID del usuario no es válido"
            )
            return
        }

        Log.d(
            "ITINERARIO_TEST",
            "Llamando al Repository con idUsuario=$idUsuario"
        )

        obtenerItinerarios(idUsuario)
    }
}