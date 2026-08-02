package com.example.gestion_de_ervicios_turisticos.reservas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.reservas.model.EstadoReserva
import com.example.gestion_de_ervicios_turisticos.reservas.model.Reserva
import com.example.gestion_de_ervicios_turisticos.reservas.viewmodel.HistorialViewModel
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

@Composable
fun HistorialReservasScreen(
    onVolver: () -> Unit,
    viewModel: HistorialViewModel = viewModel()
) {
    val estadoSeleccionado by viewModel.estadoSeleccionado.collectAsState()
    val reservas by viewModel.reservasFiltradas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header con degradado, igual que en Perfil
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(ColoresSaranta.VerdeOscuro, ColoresSaranta.AzulOscuro)
                    )
                )
                .padding(top = 48.dp, bottom = 20.dp, start = 8.dp, end = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Text(
                    text = "Historial de reservas",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chips de filtro por estado
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(EstadoReserva.entries) { estado ->
                FilterChip(
                    selected = estado == estadoSeleccionado,
                    onClick = { viewModel.seleccionarEstado(estado) },
                    label = { Text(estado.etiqueta) },
                    shape = RoundedCornerShape(50),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = ColoresSaranta.VerdeOscuro,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (reservas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tienes reservas en esta categoría.",
                    color = ColoresSaranta.Negro.copy(alpha = 0.6f)
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(reservas) { reserva ->
                    TarjetaReserva(reserva)
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun TarjetaReserva(reserva: Reserva) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(ColoresSaranta.Crema, ColoresSaranta.Crema.copy(alpha = 0.4f))
                )
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(reserva.nombreServicio, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = ColoresSaranta.Negro)
            Text("📍 ${reserva.destino}", fontSize = 13.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
            Text("📅 ${reserva.fecha}", fontSize = 13.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
        }

        Column(horizontalAlignment = Alignment.End) {
            EtiquetaEstado(estado = reserva.estado)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "S/ ${reserva.precio.toInt()}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = ColoresSaranta.VerdeOscuro
            )
        }
    }
}

@Composable
private fun EtiquetaEstado(estado: EstadoReserva) {
    val color = when (estado) {
        EstadoReserva.CONFIRMADA -> ColoresSaranta.Verde
        EstadoReserva.PENDIENTE -> ColoresSaranta.Dorado
        EstadoReserva.COMPLETADA -> ColoresSaranta.VerdeOscuro
        EstadoReserva.CANCELADA -> Color(0xFFC62828)
        EstadoReserva.TODAS -> ColoresSaranta.AzulOscuro
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(text = estado.etiqueta, fontSize = 11.sp, color = color, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialReservasScreenPreview() {
    HistorialReservasScreen(onVolver = {})
}