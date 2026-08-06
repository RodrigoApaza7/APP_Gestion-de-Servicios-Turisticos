package com.example.gestion_de_ervicios_turisticos.network

import com.example.gestion_de_ervicios_turisticos.model.request.LoginRequest
import com.example.gestion_de_ervicios_turisticos.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/Auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

}