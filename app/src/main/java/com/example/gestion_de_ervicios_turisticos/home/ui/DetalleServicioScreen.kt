package com.example.gestion_de_ervicios_turisticos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.home.data.ServicioRepository
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio

@Composable
fun DetalleServicioScreen(
    servicioId: String,
    onVolver: () -> Unit,
    onReservar: (Servicio) -> Unit,
    repository: ServicioRepository = remember { ServicioRepository() }
) {
    // Como no hay operación async real (es mock local), basta con recordar el resultado.
    // Si en el futuro esto viene de una API, aquí es donde cambiaría a un ViewModel con estado de carga.
    val servicio = remember(servicioId) { repository.obtenerPorId(servicioId) }

    if (servicio == null) {
        ServicioNoEncontrado(onVolver = onVolver)
        return
    }

    var esFavorito by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Imagen de cabecera con botones flotantes encima
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color(0xFFB8C9C6)) // TODO: reemplazar por imagen real del servicio
            ) {
                // TODO: Image(painter = painterResource(...), contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BotonCircular(
                        icono = Icons.Filled.ArrowBack,
                        descripcion = "Volver",
                        onClick = onVolver
                    )
                    BotonCircular(
                        icono = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        descripcion = "Favorito",
                        tint = if (esFavorito) Color.Red else Color.Black,
                        onClick = { esFavorito = !esFavorito }
                    )
                }
            }

            // Contenido con scroll, por si la descripción es larga
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp, bottom = 100.dp) // espacio para el botón fijo de abajo
            ) {
                Text(
                    text = servicio.tipo.etiqueta,
                    fontSize = 13.sp,
                    color = Color(0xFF0F4C46),
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = servicio.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("📍 ${servicio.destino}", fontSize = 14.sp, color = Color(0xFF757575))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("⭐ ${servicio.calificacion}", fontSize = 14.sp, color = Color(0xFF757575))
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = Color(0xFFE0E0E0))
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Descripción",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = servicio.descripcion,
                    fontSize = 14.sp,
                    color = Color(0xFF555555),
                    lineHeight = 20.sp
                )
            }
        }

        // Barra inferior fija: precio + botón de reservar
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Precio", fontSize = 12.sp, color = Color(0xFF9E9E9E))
                Text(
                    text = "S/ ${servicio.precio.toInt()}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { onReservar(servicio) },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F4C46)),
                modifier = Modifier.height(50.dp)
            ) {
                Text("Reservar", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun BotonCircular(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    tint: Color = Color.Black,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.9f)),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(imageVector = icono, contentDescription = descripcion, tint = tint)
        }
    }
}

@Composable
private fun ServicioNoEncontrado(onVolver: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("No se encontró el servicio.", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleServicioScreenPreview() {
    DetalleServicioScreen(
        servicioId = "1", // debe coincidir con un id real de tu ServicioRepository
        onVolver = {},
        onReservar = {}
    )
}