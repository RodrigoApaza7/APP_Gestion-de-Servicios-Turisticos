package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

// Estados de la rana para el flujo de LOGIN
enum class EstadoRanaLogin(val mensaje: String) {
    BIENVENIDA("¡Hola! Qué bueno verte otra vez."),
    ESCRIBIENDO_CORREO("Escribiendo tu correo..."),
    ESCRIBIENDO_CONTRASENA("Cuidando tu contraseña 🔒"),
    LOGIN_CORRECTO("¡Excelente! Vamos a viajar."),
    CONTRASENA_INCORRECTA("Ups... esa contraseña no coincide."),
    SIN_INTERNET("Sin conexión. Verifica tu internet.")
}

// Estados de la rana para el flujo de REGISTRO
enum class EstadoRanaRegistro(val mensaje: String) {
    INICIO("¡Comencemos! Completa tus datos."),
    NOMBRE_COMPLETADO("La rana se prepara para el viaje."),
    CORREO_COMPLETADO("Obtiene su pasaporte."),
    CONTRASENA_COMPLETADA("Añade su brújula."),
    TODO_COMPLETADO("Está lista para viajar."),
    REGISTRO_EXITOSO("¡Bienvenido a Saranta! Tu aventura comienza ahora.")
}

// TODO: cuando tengas las ilustraciones reales, reemplaza cada Icon() de abajo por:
// Image(painter = painterResource(id = R.drawable.rana_xxx), contentDescription = null, modifier = Modifier.fillMaxSize())
@Composable
fun RanaMascotaLogin(estado: EstadoRanaLogin, modifier: Modifier = Modifier) {
    val (icono, colorFondo) = when (estado) {
        EstadoRanaLogin.BIENVENIDA -> Icons.Filled.WavingHand to ColoresSaranta.VerdeOscuro
        EstadoRanaLogin.ESCRIBIENDO_CORREO -> Icons.Filled.Email to ColoresSaranta.AzulOscuro
        EstadoRanaLogin.ESCRIBIENDO_CONTRASENA -> Icons.Filled.VisibilityOff to ColoresSaranta.AzulOscuro
        EstadoRanaLogin.LOGIN_CORRECTO -> Icons.Filled.CheckCircle to ColoresSaranta.Verde
        EstadoRanaLogin.CONTRASENA_INCORRECTA -> Icons.Filled.SentimentDissatisfied to Color3(0xFFC62828)
        EstadoRanaLogin.SIN_INTERNET -> Icons.Filled.WifiOff to Color3(0xFF757575)
    }
    RanaBase(icono = icono, colorFondo = colorFondo, mensaje = estado.mensaje, modifier = modifier)
}

@Composable
fun RanaMascotaRegistro(estado: EstadoRanaRegistro, modifier: Modifier = Modifier) {
    val (icono, colorFondo) = when (estado) {
        EstadoRanaRegistro.INICIO -> Icons.Filled.WavingHand to ColoresSaranta.VerdeOscuro
        EstadoRanaRegistro.NOMBRE_COMPLETADO -> Icons.Filled.Badge to ColoresSaranta.AzulOscuro
        EstadoRanaRegistro.CORREO_COMPLETADO -> Icons.Filled.Mail to ColoresSaranta.AzulOscuro
        EstadoRanaRegistro.CONTRASENA_COMPLETADA -> Icons.Filled.Explore to ColoresSaranta.Dorado
        EstadoRanaRegistro.TODO_COMPLETADO -> Icons.Filled.Map to ColoresSaranta.Verde
        EstadoRanaRegistro.REGISTRO_EXITOSO -> Icons.Filled.Celebration to ColoresSaranta.Verde
    }
    RanaBase(icono = icono, colorFondo = colorFondo, mensaje = estado.mensaje, modifier = modifier)
}

@Composable
private fun RanaBase(
    icono: ImageVector,
    colorFondo: androidx.compose.ui.graphics.Color,
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
                .background(colorFondo.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = colorFondo,
                modifier = Modifier.size(72.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = mensaje,
            fontSize = 13.sp,
            color = ColoresSaranta.Negro.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

// Pequeño helper porque "Color" ya está importado como material3.Color en otros archivos a veces;
// aquí lo resolvemos directo para evitar choques de import.
private fun Color3(value: Long) = androidx.compose.ui.graphics.Color(value)