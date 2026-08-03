package com.example.gestion_de_ervicios_turisticos.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_de_ervicios_turisticos.home.data.ServicioRepository
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BusquedaViewModel: ViewModel() {

    private val _textoBusqueda = MutableStateFlow("")
    val textoBusqueda: StateFlow<String> = _textoBusqueda.asStateFlow()

    // debounce(300) espera 300ms después de que el usuario deja de escribir,
    // así no busca en CADA letra tecleada (más eficiente, sensación más fluida)
    val resultados: StateFlow<List<Servicio>> = _textoBusqueda
        .debounce(300)
        .map { query -> ServicioRepository.buscar(query) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun actualizarTexto(texto: String) {
        _textoBusqueda.value = texto
    }
}