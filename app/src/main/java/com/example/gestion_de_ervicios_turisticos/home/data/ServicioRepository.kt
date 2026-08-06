package com.example.gestion_de_ervicios_turisticos.home.data

import androidx.compose.runtime.mutableStateListOf
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio

object ServicioRepository {

<<<<<<< HEAD
    private val servicios = listOf(
        Servicio("1", "Hotel Titikaka", TipoServicio.HOTEL, "Puno", 150.0, 4.5, esDestacado = true, latitud = -15.8123, longitud = -70.0218),
        Servicio("2", "Tour Islas Uros", TipoServicio.TOUR, "Puno", 80.0, 4.8, esDestacado = true, latitud = -15.8250, longitud = -69.9740),
        Servicio("3", "Restaurante Mojsa", TipoServicio.RESTAURANTE, "Cusco", 45.0, 4.6, esDestacado = true, latitud = -13.5170, longitud = -71.9780),
        Servicio("4", "Transporte Turístico Sur", TipoServicio.TRANSPORTE, "Arequipa", 60.0, 4.3, latitud = -16.3980, longitud = -71.5360),
        Servicio("5", "Hotel Casa Andina", TipoServicio.HOTEL, "Cusco", 180.0, 4.7, latitud = -13.5180, longitud = -71.9770),
        Servicio("6", "Tour Valle Sagrado", TipoServicio.TOUR, "Cusco", 95.0, 4.9, esDestacado = true, latitud = -13.5200, longitud = -71.9700),
        Servicio("7", "Restaurante Chicha", TipoServicio.RESTAURANTE, "Arequipa", 50.0, 4.4, latitud = -16.3990, longitud = -71.5370),
        Servicio("8", "Hotel Libertador", TipoServicio.HOTEL, "Arequipa", 200.0, 4.6, latitud = -16.4020, longitud = -71.5390),
        Servicio("9", "Tour Islas Flotantes", TipoServicio.TOUR, "Puno", 70.0, 4.5, latitud = -15.8300, longitud = -69.9800),
        Servicio("10", "Transporte Aeropuerto", TipoServicio.TRANSPORTE, "Cusco", 30.0, 4.2, latitud = -13.5250, longitud = -71.9600),
        Servicio("11", "Restaurante Tunupa", TipoServicio.RESTAURANTE, "Puno", 55.0, 4.7, latitud = -15.8405, longitud = -70.0250),
        Servicio("12", "Hotel Sonesta Posadas", TipoServicio.HOTEL, "Puno", 170.0, 4.5, latitud = -15.8150, longitud = -70.0150)
=======
    val servicios = mutableStateListOf(
        Servicio("1", "Hotel Titikaka", TipoServicio.HOTEL, "Puno", 150.0, 4.5, esDestacado = true),
        Servicio("2", "Tour Islas Uros", TipoServicio.TOUR, "Puno", 80.0, 4.8, esDestacado = true),
        Servicio("3", "Restaurante Mojsa", TipoServicio.RESTAURANTE, "Cusco", 45.0, 4.6, esDestacado = true),
        Servicio("4", "Transporte Turístico Sur", TipoServicio.TRANSPORTE, "Arequipa", 60.0, 4.3),
        Servicio("5", "Hotel Casa Andina", TipoServicio.HOTEL, "Cusco", 180.0, 4.7),
        Servicio("6", "Tour Valle Sagrado", TipoServicio.TOUR, "Cusco", 95.0, 4.9, esDestacado = true),
        Servicio("7", "Restaurante Chicha", TipoServicio.RESTAURANTE, "Arequipa", 50.0, 4.4),
        Servicio("8", "Hotel Libertador", TipoServicio.HOTEL, "Arequipa", 200.0, 4.6),
        Servicio("9", "Tour Islas Flotantes", TipoServicio.TOUR, "Puno", 70.0, 4.5),
        Servicio("10", "Transporte Aeropuerto", TipoServicio.TRANSPORTE, "Cusco", 30.0, 4.2),
        Servicio("11", "Restaurante Tunupa", TipoServicio.RESTAURANTE, "Puno", 55.0, 4.7),
        Servicio("12", "Hotel Sonesta Posadas", TipoServicio.HOTEL, "Puno", 170.0, 4.5)
>>>>>>> origin/main
    )

    fun obtenerTodos(): List<Servicio> = servicios

    fun obtenerDestacados(): List<Servicio> = servicios.filter { it.esDestacado }

    fun obtenerPorId(id: String): Servicio? = servicios.find { it.id == id }

    fun buscar(query: String): List<Servicio> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return servicios.filter {
            it.nombre.lowercase().contains(q) || it.destino.lowercase().contains(q) || it.tipo.etiqueta.lowercase().contains(q)
        }
    }

    fun obtenerPorPrestador(prestadorId: String): List<Servicio> = servicios.filter { it.prestadorId == prestadorId }

    fun agregarServicio(servicio: Servicio) {
        servicios.add(servicio)
    }

    fun eliminarServicio(id: String) {
        servicios.removeAll { it.id == id }
    }
}