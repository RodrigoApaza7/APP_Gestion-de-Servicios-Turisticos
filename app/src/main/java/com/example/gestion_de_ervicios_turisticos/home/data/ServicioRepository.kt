package com.example.gestion_de_ervicios_turisticos.home.data

import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio

class ServicioRepository {

    private val servicios = listOf(
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
    )

    fun obtenerTodos(): List<Servicio> = servicios

    fun obtenerDestacados(): List<Servicio> = servicios.filter { it.esDestacado }
}