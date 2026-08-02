package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import com.example.gestion_de_ervicios_turisticos.auth.model.RolUsuario

@Composable
fun SeleccionRolScreen(
    onRolSeleccionado: (RolUsuario) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¿Cómo quieres unirte?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Elige el tipo de cuenta que mejor se adapte a ti",
            fontSize = 14.sp,
            color = Color(0xFF757575),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        TarjetaRol(
            icono = Icons.Filled.Person,
            titulo = "Soy Turista",
            descripcion = "Quiero explorar y reservar servicios turísticos",
            onClick = { onRolSeleccionado(RolUsuario.TURISTA) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TarjetaRol(
            icono = Icons.Filled.Business,
            titulo = "Soy Prestador de servicios",
            descripcion = "Quiero ofrecer mis servicios turísticos en la plataforma",
            onClick = { onRolSeleccionado(RolUsuario.PRESTADOR) }
        )
    }
}

@Composable
private fun TarjetaRol(
    icono: ImageVector,
    titulo: String,
    descripcion: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF5F5F5))
            .clickable(onClick = onClick)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = Color(0xFF0F4C46),
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(titulo, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(descripcion, fontSize = 13.sp, color = Color(0xFF757575))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeleccionRolScreenPreview() {
    SeleccionRolScreen(onRolSeleccionado = {})
}