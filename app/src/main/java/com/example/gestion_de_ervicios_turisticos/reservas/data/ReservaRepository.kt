package com.example.gestion_de_ervicios_turisticos.reservas.data

import com.example.gestion_de_ervicios_turisticos.reservas.model.EstadoReserva
import com.example.gestion_de_ervicios_turisticos.reservas.model.Reserva

class ReservaRepository {

    // Datos simulados. Cuando exista el Backend, esta clase se reemplaza
    // por llamadas a Retrofit (GET /reservas del usuario actual), sin tocar
    // el ViewModel ni la UI.
    private val reservas = listOf(
        Reserva("r1", "Hotel Titikaka", "Puno", "15 Ago 2026", 150.0, EstadoReserva.CONFIRMADA),
        Reserva("r2", "Tour Islas Uros", "Puno", "16 Ago 2026", 80.0, EstadoReserva.PENDIENTE),
        Reserva("r3", "Restaurante Mojsa", "Cusco", "02 Jul 2026", 45.0, EstadoReserva.COMPLETADA),
        Reserva("r4", "Tour Valle Sagrado", "Cusco", "20 Jun 2026", 95.0, EstadoReserva.COMPLETADA),
        Reserva("r5", "Transporte Aeropuerto", "Cusco", "10 May 2026", 30.0, EstadoReserva.CANCELADA)
    )

    fun obtenerTodas(): List<Reserva> = reservas
}