package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.auth.model.RolUsuario
import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.AuthUiState
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.RegisterViewModel
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

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
    var contrasenaVisible by remember { mutableStateOf(false) }
    var confirmarVisible by remember { mutableStateOf(false) }
    var errorLocal by remember { mutableStateOf<String?>(null) }

    val uiState by viewModel.uiState.collectAsState()

    // Campos requeridos según el rol (para saber cuándo está "todo completado")
    val camposBaseCompletos = nombre.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank() &&
            fechaNacimiento.isNotBlank() && contrasena.isNotBlank() && confirmarContrasena.isNotBlank()
    val camposPrestadorCompletos = rol != RolUsuario.PRESTADOR || (nombreNegocio.isNotBlank() && ruc.isNotBlank())
    val todoCompleto = camposBaseCompletos && camposPrestadorCompletos

    // Progresión de la rana según cuánto ha llenado el usuario
    val estadoRana = when {
        uiState is AuthUiState.Exito -> EstadoRanaRegistro.REGISTRO_EXITOSO
        todoCompleto -> EstadoRanaRegistro.TODO_COMPLETADO
        contrasena.isNotBlank() -> EstadoRanaRegistro.CONTRASENA_COMPLETADA
        correo.isNotBlank() -> EstadoRanaRegistro.CORREO_COMPLETADO
        nombre.isNotBlank() -> EstadoRanaRegistro.NOMBRE_COMPLETADO
        else -> EstadoRanaRegistro.INICIO
    }

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Exito) {
            onRegistroExitoso((uiState as AuthUiState.Exito).usuario)
        }
    }

    Scaffold(containerColor = Color.White) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text("Register", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = ColoresSaranta.Negro)
            Text(
                "¡Comencemos una nueva aventura!",
                fontSize = 15.sp,
                color = ColoresSaranta.Dorado,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp))
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RanaMascotaRegistro(estado = estadoRana)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Registrando como: ${rol.etiqueta}",
                    fontSize = 12.sp,
                    color = ColoresSaranta.AzulOscuro
                )

                Spacer(modifier = Modifier.height(18.dp))

                CampoConIcono(
                    valor = nombre, onCambio = { nombre = it },
                    placeholder = "Nombre completo", icono = Icons.Filled.Person,
                    mostrarCheck = true, completado = nombre.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))

                CampoConIcono(
                    valor = correo, onCambio = { correo = it },
                    placeholder = "Correo electrónico", icono = Icons.Filled.Email,
                    teclado = KeyboardType.Email,
                    mostrarCheck = true, completado = correo.contains("@")
                )
                Spacer(modifier = Modifier.height(10.dp))

                CampoConIcono(
                    valor = telefono, onCambio = { telefono = it },
                    placeholder = "Número de teléfono", icono = Icons.Filled.Phone,
                    teclado = KeyboardType.Phone,
                    mostrarCheck = true, completado = telefono.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))

                CampoConIcono(
                    valor = fechaNacimiento, onCambio = { fechaNacimiento = it },
                    placeholder = "Fecha de nacimiento (DD/MM/AAAA)", icono = Icons.Filled.Cake,
                    mostrarCheck = true, completado = fechaNacimiento.isNotBlank()
                )
                Spacer(modifier = Modifier.height(10.dp))

                if (rol == RolUsuario.PRESTADOR) {
                    CampoConIcono(
                        valor = nombreNegocio, onCambio = { nombreNegocio = it },
                        placeholder = "Nombre del negocio", icono = Icons.Filled.Store,
                        mostrarCheck = true, completado = nombreNegocio.isNotBlank()
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    CampoConIcono(
                        valor = ruc, onCambio = { ruc = it },
                        placeholder = "RUC", icono = Icons.Filled.Badge,
                        teclado = KeyboardType.Number,
                        mostrarCheck = true, completado = ruc.isNotBlank()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                CampoConIcono(
                    valor = contrasena, onCambio = { contrasena = it },
                    placeholder = "Contraseña", icono = Icons.Filled.Lock,
                    esPassword = true, passwordVisible = contrasenaVisible,
                    onTogglePassword = { contrasenaVisible = !contrasenaVisible }
                )
                Spacer(modifier = Modifier.height(10.dp))

                CampoConIcono(
                    valor = confirmarContrasena, onCambio = { confirmarContrasena = it },
                    placeholder = "Confirmar contraseña", icono = Icons.Filled.Lock,
                    esPassword = true, passwordVisible = confirmarVisible,
                    onTogglePassword = { confirmarVisible = !confirmarVisible }
                )

                Spacer(modifier = Modifier.height(20.dp))

                errorLocal?.let {
                    Text(it, color = Color(0xFFC62828), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                if (uiState is AuthUiState.Error) {
                    Text((uiState as AuthUiState.Error).mensaje, color = Color(0xFFC62828), fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = {
                        errorLocal = validarCampos(
                            nombre, correo, telefono, fechaNacimiento,
                            contrasena, confirmarContrasena, rol, nombreNegocio, ruc
                        )
                        if (errorLocal == null) {
                            viewModel.registrar(
                                nombre = nombre, correo = correo, contrasena = contrasena,
                                telefono = telefono, fechaNacimiento = fechaNacimiento,
                                rol = rol.valor,
                                nombreNegocio = if (rol == RolUsuario.PRESTADOR) nombreNegocio else null,
                                ruc = if (rol == RolUsuario.PRESTADOR) ruc else null
                            )
                        }
                    },
                    enabled = uiState !is AuthUiState.Cargando,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                    modifier = Modifier.fillMaxWidth().height(52.dp)
                ) {
                    if (uiState is AuthUiState.Cargando) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    } else {
                        Text("Registrarme", color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onIrALogin, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("¿Ya tienes cuenta? Inicia sesión", color = ColoresSaranta.AzulOscuro, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

private fun validarCampos(
    nombre: String, correo: String, telefono: String, fechaNacimiento: String,
    contrasena: String, confirmarContrasena: String,
    rol: RolUsuario, nombreNegocio: String, ruc: String
): String? {
    if (nombre.isBlank() || correo.isBlank() || telefono.isBlank() || fechaNacimiento.isBlank() || contrasena.isBlank()) {
        return "Completa todos los campos obligatorios"
    }
    if (!correo.contains("@")) return "Ingresa un correo válido"
    if (contrasena.length < 6) return "La contraseña debe tener al menos 6 caracteres"
    if (contrasena != confirmarContrasena) return "Las contraseñas no coinciden"
    if (rol == RolUsuario.PRESTADOR && (nombreNegocio.isBlank() || ruc.isBlank())) {
        return "Completa los datos de tu negocio (nombre y RUC)"
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rol = RolUsuario.TURISTA, onRegistroExitoso = {}, onIrALogin = {})
}