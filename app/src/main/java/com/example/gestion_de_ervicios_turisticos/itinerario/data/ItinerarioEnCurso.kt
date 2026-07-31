package com.example.gestion_de_ervicios_turisticos.itinerario.data

import androidx.compose.runtime.mutableStateListOf
import com.example.gestion_de_ervicios_turisticos.itinerario.model.ItemItinerario

object ItinerarioEnCurso {
    val items = mutableStateListOf<ItemItinerario>()

    fun agregar(item: ItemItinerario) {
        // Evita duplicados si el usuario toca "Agregar" dos veces en el mismo servicio
        if (items.none { it.servicioId == item.servicioId }) {
            items.add(item)
        }
    }

    fun eliminar(servicioId: String) {
        items.removeAll { it.servicioId == servicioId }
    }

    fun costoTotal(): Double = items.sumOf { it.precio }

    fun limpiar() {
        items.clear()
    }
}