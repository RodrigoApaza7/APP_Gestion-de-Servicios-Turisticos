package com.example.gestion_de_ervicios_turisticos.itinerario.data

import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.ActualizarDetalleItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.ActualizarItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearDetalleItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.request.CrearItinerarioRequest
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.DetalleItinerarioResponse
import com.example.gestion_de_ervicios_turisticos.itinerario.model.response.ItinerarioResponse
import com.example.gestion_de_ervicios_turisticos.network.ApiClient
import com.example.gestion_de_ervicios_turisticos.network.ApiService

class ItinerarioRepository {

    private val apiService: ApiService =
        ApiClient.retrofit.create(ApiService::class.java)

    // =========================
    // ITINERARIOS
    // =========================

    suspend fun obtenerItinerariosPorUsuario(
        idUsuario: Int
    ): Result<List<ItinerarioResponse>> {
        return try {
            val response = apiService.obtenerItinerariosPorUsuario(idUsuario)

            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(
                    Exception("Error al obtener itinerarios: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerItinerario(
        idItinerario: Int
    ): Result<ItinerarioResponse> {
        return try {
            val response = apiService.obtenerItinerario(idItinerario)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(
                    Exception("La API no devolvió el itinerario")
                )
            } else {
                Result.failure(
                    Exception("Error al obtener itinerario: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearItinerario(
        request: CrearItinerarioRequest
    ): Result<ItinerarioResponse> {
        return try {
            val response = apiService.crearItinerario(request)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(
                    Exception("La API no devolvió el itinerario creado")
                )
            } else {
                Result.failure(
                    Exception("Error al crear itinerario: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun actualizarItinerario(
        idItinerario: Int,
        request: ActualizarItinerarioRequest
    ): Result<Unit> {
        return try {
            val response = apiService.actualizarItinerario(
                idItinerario,
                request
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        "Error al actualizar itinerario: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun eliminarItinerario(
        idItinerario: Int
    ): Result<Unit> {
        return try {
            val response = apiService.eliminarItinerario(idItinerario)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        "Error al eliminar itinerario: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // =========================
    // DETALLES
    // =========================

    suspend fun obtenerDetalles(
        idItinerario: Int
    ): Result<List<DetalleItinerarioResponse>> {
        return try {
            val response = apiService.obtenerDetalles(idItinerario)

            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(
                    Exception(
                        "Error al obtener detalles: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun obtenerDetalle(
        idDetalle: Int
    ): Result<DetalleItinerarioResponse> {
        return try {
            val response = apiService.obtenerDetalle(idDetalle)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(
                    Exception("La API no devolvió el detalle")
                )
            } else {
                Result.failure(
                    Exception(
                        "Error al obtener detalle: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearDetalle(
        idItinerario: Int,
        request: CrearDetalleItinerarioRequest
    ): Result<DetalleItinerarioResponse> {
        return try {
            val response = apiService.crearDetalle(
                idItinerario,
                request
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(
                    Exception("La API no devolvió el detalle creado")
                )
            } else {
                Result.failure(
                    Exception(
                        "Error al crear detalle: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun actualizarDetalle(
        idDetalle: Int,
        request: ActualizarDetalleItinerarioRequest
    ): Result<Unit> {
        return try {
            val response = apiService.actualizarDetalle(
                idDetalle,
                request
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        "Error al actualizar detalle: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun eliminarDetalle(
        idDetalle: Int
    ): Result<Unit> {
        return try {
            val response = apiService.eliminarDetalle(idDetalle)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception(
                        "Error al eliminar detalle: ${response.code()}"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}