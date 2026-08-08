package com.example.gestion_de_ervicios_turisticos.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario

object ApiClient {

    // Cambia esta IP por la de tu computadora cuando pruebes en el celular
    private const val BASE_URL = "http://192.168.1.85:5289/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->

            val token = SesionUsuario.token.value

            val request = chain.request()
                .newBuilder()
                .apply {
                    if (!token.isNullOrBlank()) {
                        addHeader(
                            "Authorization",
                            "Bearer $token"
                        )
                    }
                }
                .build()

            chain.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}