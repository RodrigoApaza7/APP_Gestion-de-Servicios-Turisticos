package com.example.gestion_de_ervicios_turisticos.reservas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.reservas.data.ReservaRepository
import com.example.gestion_de_ervicios_turisticos.reservas.model.EstadoReserva
import com.example.gestion_de_ervicios_turisticos.reservas.model.Reserva
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistorialViewModel(
    private val repository: ReservaRepository = ReservaRepository()
) : ViewModel() {

    private val todasLasReservas: List<Reserva> = repository.obtenerTodas()

    private val _estadoSeleccionado = MutableStateFlow(EstadoReserva.TODAS)
    val estadoSeleccionado: StateFlow<EstadoReserva> = _estadoSeleccionado.asStateFlow()

    val reservasFiltradas: StateFlow<List<Reserva>> = _estadoSeleccionado
        .map { estado ->
            if (estado == EstadoReserva.TODAS) {
                todasLasReservas
            } else {
                todasLasReservas.filter { it.estado == estado }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = todasLasReservas
        )

    fun seleccionarEstado(estado: EstadoReserva) {
        _estadoSeleccionado.value = estado
    }
}