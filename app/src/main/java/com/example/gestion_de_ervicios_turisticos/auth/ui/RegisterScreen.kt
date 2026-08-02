package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.auth.model.RolUsuario
import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.AuthUiState
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.RegisterViewModel
import com.example.gestion_de_ervicios_turisticos.ui.theme.Gestion_de_ervicios_TuristicosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    rol: RolUsuario,
    onRegistroExitoso: (User) -> Unit,
    onIrALogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var nombreNegocio by remember { mutableStateOf("") }
    var ruc by remember { mutableStateOf("") }

    var errorLocal by remember { mutableStateOf<String?>(null) }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Exito) {
            onRegistroExitoso((uiState as AuthUiState.Exito).usuario)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Registro",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // el form ya no cabe en una pantalla, necesita scroll
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFBDBDBD).copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Registrando como: ${rol.etiqueta}",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            CampoTexto(valor = nombre, onCambio = { nombre = it }, placeholder = "Nombre Completo")
            Spacer(modifier = Modifier.height(12.dp))

            CampoTexto(valor = correo, onCambio = { correo = it }, placeholder = "Correo Electrónico", teclado = KeyboardType.Email)
            Spacer(modifier = Modifier.height(12.dp))

            CampoTexto(valor = telefono, onCambio = { telefono = it }, placeholder = "Número de teléfono", teclado = KeyboardType.Phone)
            Spacer(modifier = Modifier.height(12.dp))

            CampoTexto(valor = fechaNacimiento, onCambio = { fechaNacimiento = it }, placeholder = "Fecha de nacimiento (DD/MM/AAAA)")
            Spacer(modifier = Modifier.height(12.dp))

            // Campos extra SOLO si el rol es Prestador
            if (rol == RolUsuario.PRESTADOR) {
                CampoTexto(valor = nombreNegocio, onCambio = { nombreNegocio = it }, placeholder = "Nombre del negocio")
                Spacer(modifier = Modifier.height(12.dp))

                CampoTexto(valor = ruc, onCambio = { ruc = it }, placeholder = "RUC", teclado = KeyboardType.Number)
                Spacer(modifier = Modifier.height(12.dp))
            }

            CampoTexto(valor = contrasena, onCambio = { contrasena = it }, placeholder = "Contraseña", esPassword = true)
            Spacer(modifier = Modifier.height(12.dp))

            CampoTexto(valor = confirmarContrasena, onCambio = { confirmarContrasena = it }, placeholder = "Confirmar contraseña", esPassword = true)
            Spacer(modifier = Modifier.height(20.dp))

            // Error local (validación de campos, antes de llamar al ViewModel)
            errorLocal?.let {
                Text(text = it, color = Color(0xFFFF8A80))
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Error del servidor/repositorio (ej: correo ya registrado)
            if (uiState is AuthUiState.Error) {
                Text(
                    text = (uiState as AuthUiState.Error).mensaje,
                    color = Color(0xFFFF8A80)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Button(
                onClick = {
                    errorLocal = validarCampos(
                        nombre, correo, telefono, fechaNacimiento,
                        contrasena, confirmarContrasena,
                        rol, nombreNegocio, ruc
                    )
                    if (errorLocal == null) {
                        viewModel.registrar(
                            nombre = nombre,
                            correo = correo,
                            contrasena = contrasena,
                            telefono = telefono,
                            fechaNacimiento = fechaNacimiento,
                            rol = rol.valor,
                            nombreNegocio = if (rol == RolUsuario.PRESTADOR) nombreNegocio else null,
                            ruc = if (rol == RolUsuario.PRESTADOR) ruc else null
                        )
                    }
                },
                enabled = uiState !is AuthUiState.Cargando,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black.copy(alpha = 0.75f)),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                if (uiState is AuthUiState.Cargando) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                } else {
                    Text("Registrarme", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onIrALogin) {
                Text("¿Ya tienes cuenta? Inicia sesión", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CampoTexto(
    valor: String,
    onCambio: (String) -> Unit,
    placeholder: String,
    teclado: KeyboardType = KeyboardType.Text,
    esPassword: Boolean = false
) {
    TextField(
        value = valor,
        onValueChange = onCambio,
        placeholder = { Text(placeholder, color = Color.White.copy(alpha = 0.8f)) },
        singleLine = true,
        visualTransformation = if (esPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = teclado),
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White.copy(alpha = 0.25f),
            focusedContainerColor = Color.White.copy(alpha = 0.35f),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

// Validación simple ANTES de llamar al backend/mock. Devuelve el mensaje de error,
// o null si todo está bien.
private fun validarCampos(
    nombre: String,
    correo: String,
    telefono: String,
    fechaNacimiento: String,
    contrasena: String,
    confirmarContrasena: String,
    rol: RolUsuario,
    nombreNegocio: String,
    ruc: String
): String? {
    if (nombre.isBlank() || correo.isBlank() || telefono.isBlank() || fechaNacimiento.isBlank() || contrasena.isBlank()) {
        return "Completa todos los campos obligatorios"
    }
    if (!correo.contains("@")) {
        return "Ingresa un correo válido"
    }
    if (contrasena.length < 6) {
        return "La contraseña debe tener al menos 6 caracteres"
    }
    if (contrasena != confirmarContrasena) {
        return "Las contraseñas no coinciden"
    }
    if (rol == RolUsuario.PRESTADOR && (nombreNegocio.isBlank() || ruc.isBlank())) {
        return "Completa los datos de tu negocio (nombre y RUC)"
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    Gestion_de_ervicios_TuristicosTheme {
        RegisterScreen(
            rol = RolUsuario.PRESTADOR, // pruébalo también con RolUsuario.TURISTA para ver la diferencia
            onRegistroExitoso = {},
            onIrALogin = {}
        )
    }
}