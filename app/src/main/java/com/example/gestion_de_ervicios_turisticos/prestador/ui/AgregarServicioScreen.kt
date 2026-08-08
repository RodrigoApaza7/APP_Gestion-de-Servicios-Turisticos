package com.example.gestion_de_ervicios_turisticos.prestador.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import com.example.gestion_de_ervicios_turisticos.home.data.ServicioRepository
import com.example.gestion_de_ervicios_turisticos.home.model.Servicio
import com.example.gestion_de_ervicios_turisticos.home.model.TipoServicio
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarServicioScreen(
    onVolver: () -> Unit,
    onGuardadoExitoso: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(TipoServicio.HOTEL) }
    var expandido by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val usuario = SesionUsuario.usuarioActual.value

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(colors = listOf(ColoresSaranta.VerdeOscuro, ColoresSaranta.AzulOscuro)))
                .padding(top = 48.dp, bottom = 20.dp, start = 8.dp, end = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onVolver) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                }
                Text("Nuevo servicio", fontSize = 19.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            OutlinedTextField(
                value = nombre, onValueChange = { nombre = it },
                label = { Text("Nombre del servicio") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Selector de tipo
            ExposedDropdownMenuBox(expanded = expandido, onExpandedChange = { expandido = it }) {
                OutlinedTextField(
                    value = tipoSeleccionado.etiqueta,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de servicio") },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                    ExposedDropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
                    TipoServicio.entries.filter { it != TipoServicio.TODOS }.forEach { tipo ->
                        DropdownMenuItem(
                            text = { Text(tipo.etiqueta) },
                            onClick = { tipoSeleccionado = tipo; expandido = false }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = destino, onValueChange = { destino = it },
                label = { Text("Destino (ej: Puno)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = precio, onValueChange = { precio = it },
                label = { Text("Precio (S/)") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = descripcion, onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            error?.let {
                Text(it, color = Color(0xFFC62828), fontSize = 13.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = {
                    val precioDouble = precio.toDoubleOrNull()
                    if (nombre.isBlank() || destino.isBlank() || precioDouble == null) {
                        error = "Completa nombre, destino y un precio válido"
                        return@Button
                    }
                    ServicioRepository.agregarServicio(
                        Servicio(
                            id = "s${System.currentTimeMillis()}",
                            nombre = nombre,
                            tipo = tipoSeleccionado,
                            destino = destino,
                            precio = precioDouble,
                            calificacion = 0.0,
                            descripcion = descripcion.ifBlank { "Sin descripción." },
                            prestadorId = usuario?.id
                        )
                    )
                    onGuardadoExitoso()
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Publicar servicio", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgregarServicioScreenPreview() {
    AgregarServicioScreen(onVolver = {}, onGuardadoExitoso = {})
}