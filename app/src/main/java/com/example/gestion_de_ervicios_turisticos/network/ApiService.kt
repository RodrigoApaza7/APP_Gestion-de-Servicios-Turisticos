package com.example.gestion_de_ervicios_turisticos.network

import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.ActualizarDetalleItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.ActualizarItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearDetalleItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.DetalleItinerarioResponse
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.ItinerarioResponse
import com.example.gestion_de_ervicios_turisticos.model.request.LoginRequest
import com.example.gestion_de_ervicios_turisticos.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    // =========================
    // AUTH
    // =========================

    @POST("api/Auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>


    // =========================
    // ITINERARIOS
    // =========================

    @GET("api/Itinerarios/usuario/{idUsuario}")
    suspend fun obtenerItinerariosPorUsuario(
        @Path("idUsuario") idUsuario: Int
    ): Response<List<ItinerarioResponse>>

    @GET("api/Itinerarios/{idItinerario}")
    suspend fun obtenerItinerario(
        @Path("idItinerario") idItinerario: Int
    ): Response<ItinerarioResponse>

    @POST("api/Itinerarios")
    suspend fun crearItinerario(
        @Body request: CrearItinerarioRequest
    ): Response<ItinerarioResponse>

    @PUT("api/Itinerarios/{idItinerario}")
    suspend fun actualizarItinerario(
        @Path("idItinerario") idItinerario: Int,
        @Body request: ActualizarItinerarioRequest
    ): Response<Unit>

    @DELETE("api/Itinerarios/{idItinerario}")
    suspend fun eliminarItinerario(
        @Path("idItinerario") idItinerario: Int
    ): Response<Unit>


    // =========================
    // DETALLES DE ITINERARIO
    // =========================

    @GET("api/Itinerarios/{idItinerario}/detalles")
    suspend fun obtenerDetalles(
        @Path("idItinerario") idItinerario: Int
    ): Response<List<DetalleItinerarioResponse>>

    @GET("api/Itinerarios/detalles/{idDetalle}")
    suspend fun obtenerDetalle(
        @Path("idDetalle") idDetalle: Int
    ): Response<DetalleItinerarioResponse>

    @POST("api/Itinerarios/{idItinerario}/detalles")
    suspend fun crearDetalle(
        @Path("idItinerario") idItinerario: Int,
        @Body request: CrearDetalleItinerarioRequest
    ): Response<DetalleItinerarioResponse>

    @PUT("api/Itinerarios/detalles/{idDetalle}")
    suspend fun actualizarDetalle(
        @Path("idDetalle") idDetalle: Int,
        @Body request: ActualizarDetalleItinerarioRequest
    ): Response<Unit>

    @DELETE("api/Itinerarios/detalles/{idDetalle}")
    suspend fun eliminarDetalle(
        @Path("idDetalle") idDetalle: Int
    ): Response<Unit>
}