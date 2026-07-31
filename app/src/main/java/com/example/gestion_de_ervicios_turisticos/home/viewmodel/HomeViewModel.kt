package com.example.gestion_de_ervicios_turisticos.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.home.data.ServicioRepository
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: ServicioRepository = ServicioRepository()
) : ViewModel() {

    private val todosLosServicios: List<Servicio> = repository.obtenerTodos()

    val serviciosDestacados: List<Servicio> = repository.obtenerDestacados()

    private val _tipoSeleccionado = MutableStateFlow(TipoServicio.TODOS)
    val tipoSeleccionado: StateFlow<TipoServicio> = _tipoSeleccionado.asStateFlow()

    // Se recalcula automáticamente cada vez que cambia el tipo seleccionado
    val serviciosFiltrados: StateFlow<List<Servicio>> = _tipoSeleccionado
        .map { tipo ->
            if (tipo == TipoServicio.TODOS) {
                todosLosServicios
            } else {
                todosLosServicios.filter { it.tipo == tipo }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = todosLosServicios
        )

    fun seleccionarTipo(tipo: TipoServicio) {
        _tipoSeleccionado.value = tipo
    }
}