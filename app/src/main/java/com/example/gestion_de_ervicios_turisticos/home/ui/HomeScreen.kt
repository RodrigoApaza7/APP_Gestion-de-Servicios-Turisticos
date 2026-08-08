package com.example.gestion_de_ervicios_turisticos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio
import com.example.gestion_de_ervicios_turisticos.home.viewmodel.HomeViewModel
import com.example.gestion_de_ervicios_turisticos.notificaciones.data.NotificacionRepository
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

@Composable
fun HomeScreen(
    onServicioClick: (Servicio) -> Unit,
    onIrAItinerario: () -> Unit,
    onIrABuscar: () -> Unit,
    onIrAPerfil: () -> Unit,
    onIrANotificaciones: () -> Unit,   // <- nuevo
    viewModel: HomeViewModel = viewModel()
) {
    val tipoSeleccionado by viewModel.tipoSeleccionado.collectAsState()
    val serviciosFiltrados by viewModel.serviciosFiltrados.collectAsState()
    val destacados = viewModel.serviciosDestacados

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumnConScroll(
            tipoSeleccionado = tipoSeleccionado,
            serviciosFiltrados = serviciosFiltrados,
            destacados = destacados,
            onTipoSeleccionado = viewModel::seleccionarTipo,
            onServicioClick = onServicioClick,
            onIrANotificaciones = onIrANotificaciones   // <- nuevo
        )

        BottomNavFlotante(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            onIrAInicio = { /* ya estamos aquí */ },
            onIrAItinerario = onIrAItinerario,
            onIrABuscar = onIrABuscar,   // <- ahora usa el parámetro que recibe HomeScreen, no un TODO vacío
            onIrAPerfil = onIrAPerfil
        )
    }
}

@Composable
private fun LazyColumnConScroll(
    tipoSeleccionado: TipoServicio,
    serviciosFiltrados: List<Servicio>,
    destacados: List<Servicio>,
    onTipoSeleccionado: (TipoServicio) -> Unit,
    onServicioClick: (Servicio) -> Unit,
    onIrANotificaciones: () -> Unit   // <- nuevo
) {
    androidx.compose.foundation.lazy.LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item { EncabezadoInicio(onIrANotificaciones = onIrANotificaciones) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            CategoriasChips(
                tipoSeleccionado = tipoSeleccionado,
                onTipoSeleccionado = onTipoSeleccionado
            )
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        if (destacados.isNotEmpty()) {
            item { CarruselDestacados(destacados = destacados, onServicioClick = onServicioClick) }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recomendados", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.Negro)
                TextButton(onClick = { /* TODO: ver todo el catálogo si separan pantalla más adelante */ }) {
                    Text("Ver todo", color = ColoresSaranta.VerdeOscuro)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            GridDeServicios(
                servicios = serviciosFiltrados,
                onServicioClick = onServicioClick
            )
        }
    }
}

@Composable
private fun EncabezadoInicio(onIrANotificaciones: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            IconButton(onClick = onIrANotificaciones) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notificaciones", tint = ColoresSaranta.Negro)
            }
            if (NotificacionRepository.hayNoLeidas()) {
                Box(
                    modifier = Modifier
                        .size(9.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-6).dp, y = 6.dp)
                        .clip(CircleShape)
                        .background(ColoresSaranta.Dorado)
                )
            }
        }
        Text("Descubre", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.Negro)
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(ColoresSaranta.Crema),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Perfil", tint = ColoresSaranta.AzulOscuro)
        }
    }
}

@Composable
private fun CategoriasChips(
    tipoSeleccionado: TipoServicio,
    onTipoSeleccionado: (TipoServicio) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(TipoServicio.entries) { tipo ->
            val seleccionado = tipo == tipoSeleccionado
            FilterChip(
                selected = seleccionado,
                onClick = { onTipoSeleccionado(tipo) },
                label = { Text(tipo.etiqueta) },
                shape = RoundedCornerShape(50),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = ColoresSaranta.VerdeOscuro,
                    selectedLabelColor = Color.White
                )
            )
        }
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
private fun CarruselDestacados(
    destacados: List<Servicio>,
    onServicioClick: (Servicio) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { destacados.size })

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) { page ->
            val servicio = destacados[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(ColoresSaranta.VerdeOscuro) // TODO: reemplazar por imagen real del servicio
                    .clickable(onClick = { onServicioClick(servicio) })
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                // TODO: Image(painter = painterResource(...), contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                Column {
                    Text(servicio.nombre, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("⭐ ${servicio.calificacion}", color = ColoresSaranta.Dorado, fontSize = 13.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(destacados.size) { index ->
                val activo = index == pagerState.currentPage
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (activo) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (activo) ColoresSaranta.VerdeOscuro else Color(0xFFD9D9D9))
                )
            }
        }
    }
}

@Composable
private fun GridDeServicios(
    servicios: List<Servicio>,
    onServicioClick: (Servicio) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        servicios.chunked(2).forEach { filaDeDos ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                filaDeDos.forEach { servicio ->
                    Box(modifier = Modifier.weight(1f)) {
                        TarjetaServicio(servicio = servicio, onClick = { onServicioClick(servicio) })
                    }
                }
                if (filaDeDos.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun TarjetaServicio(servicio: Servicio, onClick: () -> Unit) {
    var esFavorito by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ColoresSaranta.Crema)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(ColoresSaranta.AzulOscuro) // TODO: reemplazar por imagen real del servicio
        ) {
            // TODO: Image(painter = painterResource(...), contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            IconButton(
                onClick = { esFavorito = !esFavorito },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (esFavorito) ColoresSaranta.Dorado else Color.White
                )
            }
        }
        Column(modifier = Modifier.padding(10.dp)) {
            Text(servicio.nombre, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = ColoresSaranta.Negro)
            Text("📍 ${servicio.destino}", fontSize = 12.sp, color = ColoresSaranta.AzulOscuro.copy(alpha = 0.7f))
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("⭐ ${servicio.calificacion}", fontSize = 12.sp, color = ColoresSaranta.Negro)
                Text("S/ ${servicio.precio.toInt()}", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.VerdeOscuro)
            }
        }
    }
}

@Composable
private fun BottomNavFlotante(
    modifier: Modifier = Modifier,
    onIrAInicio: () -> Unit,
    onIrAItinerario: () -> Unit,
    onIrABuscar: () -> Unit,
    onIrAPerfil: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onIrAInicio) {
            Icon(Icons.Filled.Home, contentDescription = "Inicio", tint = ColoresSaranta.VerdeOscuro)
        }
        IconButton(onClick = onIrAItinerario) {
            Icon(Icons.Filled.DateRange, contentDescription = "Itinerario", tint = Color(0xFF9E9E9E))
        }
        IconButton(onClick = onIrABuscar) {
            Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color(0xFF9E9E9E))
        }
        IconButton(onClick = onIrAPerfil) {
            Icon(Icons.Filled.Person, contentDescription = "Perfil", tint = Color(0xFF9E9E9E))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onServicioClick = {},
        onIrAItinerario = {},
        onIrABuscar = {},
        onIrAPerfil = {},
        onIrANotificaciones = {}   // <- agrega esta línea
    )
}