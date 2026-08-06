package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.R
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

// Estados de la rana para el flujo de LOGIN
enum class EstadoRanaLogin(val mensaje: String, val drawableRes: Int) {
    BIENVENIDA("¡Hola! Qué bueno verte otra vez.", R.drawable.rana_bienvenida),
    ESCRIBIENDO_CORREO("Escribiendo tu correo...", R.drawable.rana_correo),
    ESCRIBIENDO_CONTRASENA("Cuidando tu contraseña 🔒", R.drawable.rana_contrasena),
    LOGIN_CORRECTO("¡Excelente! Vamos a viajar.", R.drawable.rana_exito),
    CONTRASENA_INCORRECTA("Ups... esa contraseña no coincide.", R.drawable.rana_error),
    SIN_INTERNET("Sin conexión. Verifica tu internet.", R.drawable.rana_error) // TODO: reemplazar cuando tengas la imagen específica de "sin internet"
}

// Estados de la rana para el flujo de REGISTRO
enum class EstadoRanaRegistro(val mensaje: String, val drawableRes: Int) {
    INICIO("¡Comencemos! Completa tus datos.", R.drawable.inicio_register),
    NOMBRE_COMPLETADO("La rana se prepara para el viaje.", R.drawable.inicio_register), // TODO: reemplazar cuando tengas la imagen específica de "nombre completado"
    CORREO_COMPLETADO("Obtiene su pasaporte.", R.drawable.correo_completado),
    CONTRASENA_COMPLETADA("Añade su brújula.", R.drawable.contrasena_completa),
    TODO_COMPLETADO("Está lista para viajar.", R.drawable.registro_completado),
    REGISTRO_EXITOSO("¡Bienvenido a Saranta! Tu aventura comienza ahora.", R.drawable.registro_exitoso)
}

@Composable
fun RanaMascotaLogin(estado: EstadoRanaLogin, modifier: Modifier = Modifier) {
    RanaBase(drawableRes = estado.drawableRes, mensaje = estado.mensaje, modifier = modifier)
}

@Composable
fun RanaMascotaRegistro(estado: EstadoRanaRegistro, modifier: Modifier = Modifier) {
    RanaBase(drawableRes = estado.drawableRes, mensaje = estado.mensaje, modifier = modifier)
}

@Composable
private fun RanaBase(
    drawableRes: Int,
    mensaje: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(ColoresSaranta.Crema),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        androidx.compose.material3.Text(
            text = mensaje,
            fontSize = 13.sp,
            color = ColoresSaranta.Negro.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}