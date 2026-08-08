package com.example.gestion_de_ervicios_turisticos.itinerario.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.itinerario.data.ItinerarioEnCurso
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.itinerario.viewmodel.ItinerarioUiState
import com.example.gestion_de_ervicios_turisticos.itinerario.viewmodel.ItinerarioViewModel
import androidx.compose.runtime.LaunchedEffect
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario

@Composable
fun ItinerarioScreen(
    onConfirmarItinerario: () -> Unit,
    viewModel: ItinerarioViewModel = viewModel()
) {

    val usuario = SesionUsuario.usuarioActual.value

    LaunchedEffect(usuario?.id) {
        if (usuario != null) {
            viewModel.cargarItinerariosDeSesion()
        }
    }
    val items = ItinerarioEnCurso.items // mutableStateListOf ya es observable, no necesita collectAsState

    Column(modifier = Modifier.fillMaxSize()) {
        // Mapa del itinerario (Mapbox v11)
        MapaItinerario(
            items = items,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Text(
            text = "Mi Itinerario",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        )

        if (items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Todavía no agregaste servicios a tu itinerario.\nExplora el catálogo y toca \"Agregar al itinerario\".",
                    color = Color(0xFF757575),
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFFF5F5F5))
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(item.nombreServicio, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            Text("📍 ${item.destino}", fontSize = 12.sp, color = Color(0xFF757575))
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("S/ ${item.precio.toInt()}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            IconButton(onClick = { ItinerarioEnCurso.eliminar(item.servicioId) }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Quitar", tint = Color(0xFFC62828))
                            }
                        }
                    }
                }
            }

            // Calculadora de costos + botón de confirmar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                HorizontalDivider(color = Color(0xFFE0E0E0))
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Costo total", fontSize = 15.sp, color = Color(0xFF757575))
                    Text(
                        text = "S/ ${ItinerarioEnCurso.costoTotal().toInt()}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onConfirmarItinerario,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F4C46)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Confirmar itinerario", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItinerarioScreenPreview() {
    ItinerarioScreen(onConfirmarItinerario = {})
}