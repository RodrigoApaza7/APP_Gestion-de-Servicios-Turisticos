package com.example.gestion_de_ervicios_turisticos.prestador.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import com.example.gestion_de_ervicios_turisticos.home.data.ServicioRepository
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta
import androidx.compose.foundation.layout.offset

@Composable
fun PrestadorDashboardScreen(
    onAgregarServicio: () -> Unit,
    onVerServicio: (Servicio) -> Unit,
    onIrAPerfil: () -> Unit
) {
    val usuario = SesionUsuario.usuarioActual.value
    val misServicios = usuario?.let { ServicioRepository.obtenerPorPrestador(it.id) } ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header con degradado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(colors = listOf(ColoresSaranta.VerdeOscuro, ColoresSaranta.AzulOscuro))
                )
                .padding(top = 48.dp, bottom = 24.dp, start = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Hola, ${usuario?.nombreNegocio ?: usuario?.nombre ?: "Prestador"}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Panel de gestión", fontSize = 13.sp, color = Color.White.copy(alpha = 0.75f))
                }
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                        .clickable(onClick = onIrAPerfil),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Logout, contentDescription = "Perfil", tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }

        // Tarjetas de estadísticas (superpuestas, mismo efecto que Perfil)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = (-20).dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TarjetaEstadistica(
                modifier = Modifier.weight(1f),
                valor = misServicios.size.toString(),
                etiqueta = "Servicios activos"
            )
            TarjetaEstadistica(
                modifier = Modifier.weight(1f),
                valor = "S/ ${misServicios.sumOf { it.precio }.toInt()}",
                etiqueta = "Valor catálogo"
            )
            TarjetaEstadistica(
                modifier = Modifier.weight(1f),
                valor = if (misServicios.isNotEmpty()) String.format("%.1f", misServicios.map { it.calificacion }.average()) else "—",
                etiqueta = "Calificación prom."
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mis servicios", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.Negro)
            IconButton(onClick = onAgregarServicio) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar servicio", tint = ColoresSaranta.VerdeOscuro)
            }
        }

        if (misServicios.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Todavía no publicaste ningún servicio.",
                    color = ColoresSaranta.Negro.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onAgregarServicio,
                    colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("+ Agregar mi primer servicio", color = Color.White)
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(misServicios) { servicio ->
                    TarjetaMiServicio(
                        servicio = servicio,
                        onClick = { onVerServicio(servicio) },
                        onEliminar = { ServicioRepository.eliminarServicio(servicio.id) }
                    )
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}

@Composable
private fun TarjetaEstadistica(modifier: Modifier = Modifier, valor: String, etiqueta: String) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(ColoresSaranta.Crema)
            .padding(vertical = 14.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(valor, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.VerdeOscuro)
        Spacer(modifier = Modifier.height(2.dp))
        Text(etiqueta, fontSize = 10.sp, color = ColoresSaranta.Negro.copy(alpha = 0.6f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
    }
}

@Composable
private fun TarjetaMiServicio(servicio: Servicio, onClick: () -> Unit, onEliminar: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF5F5F5))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(servicio.nombre, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = ColoresSaranta.Negro)
            Text("${servicio.tipo.etiqueta} · 📍 ${servicio.destino}", fontSize = 12.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
            Text("S/ ${servicio.precio.toInt()} · ⭐ ${servicio.calificacion}", fontSize = 12.sp, color = ColoresSaranta.VerdeOscuro)
        }
        IconButton(onClick = onEliminar) {
            Icon(Icons.Filled.Delete, contentDescription = "Eliminar", tint = Color(0xFFC62828))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrestadorDashboardScreenPreview() {
    PrestadorDashboardScreen(onAgregarServicio = {}, onVerServicio = {}, onIrAPerfil = {})
}