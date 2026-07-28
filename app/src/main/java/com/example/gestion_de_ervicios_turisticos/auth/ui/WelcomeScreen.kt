package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestion_de_ervicios_turisticos.R

@Composable
fun WelcomeScreen(
    onIrALogin: () -> Unit,
    onIrARegistro: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
        // <- Ya NO tiene padding horizontal aquí
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        // Este bloque interno SÍ tiene el padding, solo para texto y botones
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Organiza tu próximo viaje, descubre nuevos destinos y vive experiencias sin complicaciones.",
                fontSize = 14.sp,
                color = Color(0xFF7A7A7A),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onIrALogin,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Iniciar sesión", color = Color(0xFF3A3A3A), fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onIrARegistro,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Crear Cuenta", color = Color(0xFF3A3A3A), fontWeight = FontWeight.Medium)
            }
        }

        // Empuja la imagen hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Caja de imagen SIN padding horizontal — ocupa TODO el ancho
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp), // ajusta la altura a tu gusto
            contentAlignment = Alignment.Center
        ) {
            // TODO: tu imagen aquí, de borde a borde
            Image(
                painter = painterResource(id = R.drawable.ilustracion_bienvenida),
                contentDescription = null,
                contentScale = ContentScale.Crop, // o FillWidth si quieres que se ajuste distinto
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onIrALogin = {},
        onIrARegistro = {}
    )
}