package com.example.gestion_de_ervicios_turisticos.reservas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.reservas.model.EstadoReserva
import com.example.gestion_de_ervicios_turisticos.reservas.model.Reserva
import com.example.gestion_de_ervicios_turisticos.reservas.viewmodel.HistorialViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialReservasScreen(
    onVolver: () -> Unit,
    viewModel: HistorialViewModel = viewModel()
) {
    val estadoSeleccionado by viewModel.estadoSeleccionado.collectAsState()
    val reservas by viewModel.reservasFiltradas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de reservas") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(8.dp))

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
                        shape = RoundedCornerShape(50)
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
                        color = Color(0xFF757575)
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
}

@Composable
private fun TarjetaReserva(reserva: Reserva) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(reserva.nombreServicio, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Text("📍 ${reserva.destino}", fontSize = 13.sp, color = Color(0xFF757575))
            Text("📅 ${reserva.fecha}", fontSize = 13.sp, color = Color(0xFF757575))
        }

        Column(horizontalAlignment = Alignment.End) {
            EtiquetaEstado(estado = reserva.estado)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "S/ ${reserva.precio.toInt()}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun EtiquetaEstado(estado: EstadoReserva) {
    val color = when (estado) {
        EstadoReserva.CONFIRMADA -> Color(0xFF2E7D32)
        EstadoReserva.PENDIENTE -> Color(0xFFF9A825)
        EstadoReserva.COMPLETADA -> Color(0xFF0F4C46)
        EstadoReserva.CANCELADA -> Color(0xFFC62828)
        EstadoReserva.TODAS -> Color.Gray
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