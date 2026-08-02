package com.example.gestion_de_ervicios_turisticos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.gestion_de_ervicios_turisticos.itinerario.data.ItinerarioEnCurso
import com.example.gestion_de_ervicios_turisticos.itinerario.model.ItemItinerario
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

@Composable
fun DetalleServicioScreen(
    servicioId: String,
    onVolver: () -> Unit,
    onReservar: (Servicio) -> Unit,
    repository: ServicioRepository = remember { ServicioRepository() }
) {
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
                    .background(ColoresSaranta.AzulOscuro) // TODO: reemplazar por imagen real del servicio
            ) {
                // TODO: Image(painter = painterResource(...), contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BotonCircular(
                        icono = Icons.AutoMirrored.Filled.ArrowBack,
                        descripcion = "Volver",
                        onClick = onVolver
                    )
                    BotonCircular(
                        icono = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        descripcion = "Favorito",
                        tint = if (esFavorito) ColoresSaranta.Dorado else ColoresSaranta.Negro,
                        onClick = { esFavorito = !esFavorito }
                    )
                }
            }

            // Contenido con scroll, por si la descripción es larga
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp, bottom = 100.dp)
            ) {
                Text(
                    text = servicio.tipo.etiqueta,
                    fontSize = 13.sp,
                    color = ColoresSaranta.VerdeOscuro,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = servicio.nombre,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColoresSaranta.Negro
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("📍 ${servicio.destino}", fontSize = 14.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("⭐ ${servicio.calificacion}", fontSize = 14.sp, color = ColoresSaranta.Dorado)
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(color = ColoresSaranta.Crema)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Descripción",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColoresSaranta.Negro
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = servicio.descripcion,
                    fontSize = 14.sp,
                    color = ColoresSaranta.Negro.copy(alpha = 0.7f),
                    lineHeight = 20.sp
                )
            }
        }

        // Barra inferior fija: precio + botones
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
                Text("Precio", fontSize = 12.sp, color = ColoresSaranta.Negro.copy(alpha = 0.5f))
                Text(
                    text = "S/ ${servicio.precio.toInt()}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColoresSaranta.VerdeOscuro
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        ItinerarioEnCurso.agregar(
                            ItemItinerario(
                                servicioId = servicio.id,
                                nombreServicio = servicio.nombre,
                                destino = servicio.destino,
                                precio = servicio.precio
                            )
                        )
                    },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = ColoresSaranta.VerdeOscuro),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("+ Itinerario")
                }

                Button(
                    onClick = { onReservar(servicio) },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                    modifier = Modifier.height(50.dp)
                ) {
                    Text("Reservar", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun BotonCircular(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    descripcion: String,
    tint: Color = ColoresSaranta.Negro,
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
        Text("No se encontró el servicio.", fontSize = 16.sp, color = ColoresSaranta.Negro)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro)
        ) {
            Text("Volver", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleServicioScreenPreview() {
    DetalleServicioScreen(
        servicioId = "1",
        onVolver = {},
        onReservar = {}
    )
}