package com.example.gestion_de_ervicios_turisticos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.viewmodel.BusquedaViewModel
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

@Composable
fun BusquedaScreen(
    onVolver: () -> Unit,
    onServicioClick: (Servicio) -> Unit,
    viewModel: BusquedaViewModel = viewModel()
) {
    val texto by viewModel.textoBusqueda.collectAsState()
    val resultados by viewModel.resultados.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Barra superior con campo de búsqueda integrado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVolver) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = ColoresSaranta.Negro)
            }

            TextField(
                value = texto,
                onValueChange = viewModel::actualizarTexto,
                placeholder = { Text("Buscar servicios, destinos...") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = ColoresSaranta.VerdeOscuro) },
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = ColoresSaranta.Crema,
                    focusedContainerColor = ColoresSaranta.Crema,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )
        }

        when {
            texto.isBlank() -> {
                MensajeCentro("Escribe algo para buscar servicios o destinos")
            }
            resultados.isEmpty() -> {
                MensajeCentro("No se encontraron resultados para \"$texto\"")
            }
            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(resultados) { servicio ->
                        ResultadoBusqueda(servicio = servicio, onClick = { onServicioClick(servicio) })
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultadoBusqueda(servicio: Servicio, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(ColoresSaranta.Crema)
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(ColoresSaranta.AzulOscuro)
        ) {
            // TODO: imagen real del servicio
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(servicio.nombre, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = ColoresSaranta.Negro)
            Text("${servicio.tipo.etiqueta} · 📍 ${servicio.destino}", fontSize = 12.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
        }
        Text("S/ ${servicio.precio.toInt()}", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = ColoresSaranta.VerdeOscuro)
    }
}

@Composable
private fun MensajeCentro(texto: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, color = ColoresSaranta.Negro.copy(alpha = 0.5f), fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BusquedaScreenPreview() {
    BusquedaScreen(onVolver = {}, onServicioClick = {})
}