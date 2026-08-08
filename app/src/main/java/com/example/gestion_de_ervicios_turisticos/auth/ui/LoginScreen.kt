package com.example.gestion_de_ervicios_turisticos.auth.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.auth.data.GoogleAuthUiClient
import com.example.gestion_de_ervicios_turisticos.auth.data.SesionUsuario
import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.AuthUiState
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.LoginViewModel
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginExitoso: (User) -> Unit,
    onIrARegistro: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var contrasenaVisible by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val webClientId = "318861424627-0rffd7ccfpmub21jdc0k8aptkvcudk85.apps.googleusercontent.com"
    val googleAuthClient = remember { GoogleAuthUiClient(context, webClientId) }

    // Detecta foco de cada campo para saber qué expresión mostrar
    val correoInteraction = remember { MutableInteractionSource() }
    val contrasenaInteraction = remember { MutableInteractionSource() }
    val correoEnfocado by correoInteraction.collectIsFocusedAsState()
    val contrasenaEnfocada by contrasenaInteraction.collectIsFocusedAsState()

    // Prioridad: éxito > error > escribiendo contraseña > escribiendo correo > bienvenida
    val estadoRana = when {
        uiState is AuthUiState.Exito -> EstadoRanaLogin.LOGIN_CORRECTO
        uiState is AuthUiState.Error -> EstadoRanaLogin.CONTRASENA_INCORRECTA
        contrasenaEnfocada -> EstadoRanaLogin.ESCRIBIENDO_CONTRASENA
        correoEnfocado -> EstadoRanaLogin.ESCRIBIENDO_CORREO
        else -> EstadoRanaLogin.BIENVENIDA
    }
    // TODO: cuando conectes a Retrofit real, agrega un estado de red y usa
    // EstadoRanaLogin.SIN_INTERNET cuando la petición falle por falta de conexión.

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Exito) {
            val resultado = uiState as AuthUiState.Exito

            SesionUsuario.iniciarSesion(
                user = resultado.usuario,
                token = resultado.token
            )

            onLoginExitoso(resultado.usuario)
        }
    }

    Scaffold(containerColor = Color.White) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = ColoresSaranta.Negro
            )
            Text(
                text = "¡Bienvenido de nuevo!",
                fontSize = 15.sp,
                color = ColoresSaranta.Dorado,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tarjeta blanca con sombra, como el mockup
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 6.dp, shape = RoundedCornerShape(20.dp))
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RanaMascotaLogin(estado = estadoRana)

                Spacer(modifier = Modifier.height(24.dp))

                CampoConIcono(
                    valor = correo,
                    onCambio = { correo = it },
                    placeholder = "usuario@email.com",
                    icono = Icons.Filled.Email,
                    interactionSource = correoInteraction
                )

                Spacer(modifier = Modifier.height(12.dp))

                CampoConIcono(
                    valor = contrasena,
                    onCambio = { contrasena = it },
                    placeholder = "Contraseña",
                    icono = Icons.Filled.Lock,
                    esPassword = true,
                    passwordVisible = contrasenaVisible,
                    onTogglePassword = { contrasenaVisible = !contrasenaVisible },
                    interactionSource = contrasenaInteraction
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (uiState is AuthUiState.Error) {
                    Text(
                        text = (uiState as AuthUiState.Error).mensaje,
                        color = Color(0xFFC62828),
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = { viewModel.iniciarSesion(correo, contrasena) },
                    enabled = uiState !is AuthUiState.Cargando,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = ColoresSaranta.VerdeOscuro),
                    modifier = Modifier.fillMaxWidth().height(52.dp)
                ) {
                    if (uiState is AuthUiState.Cargando) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    } else {
                        Text("Ingresar", color = Color.White, fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    onClick = {
                        scope.launch {
                            val result = googleAuthClient.signIn()
                            result.onSuccess { credential ->
                                Toast.makeText(context, "Sesión con Google exitosa", Toast.LENGTH_SHORT).show()
                                viewModel.iniciarSesionConGoogle(credential)
                            }.onFailure { error ->
                                Toast.makeText(context, "Error: ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    shape = RoundedCornerShape(50),
                    color = Color.White,
                    border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                    modifier = Modifier.fillMaxWidth().height(52.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("G", color = Color(0xFF4285F4), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Continuar con Google", color = ColoresSaranta.Negro, fontSize = 15.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onIrARegistro, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("¿No tienes cuenta? Regístrate", color = ColoresSaranta.AzulOscuro, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CampoConIcono(
    valor: String,
    onCambio: (String) -> Unit,
    placeholder: String,
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    interactionSource: MutableInteractionSource,
    esPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        placeholder = { Text(placeholder, color = ColoresSaranta.Negro.copy(alpha = 0.4f)) },
        leadingIcon = { Icon(icono, contentDescription = null, tint = ColoresSaranta.VerdeOscuro) },
        trailingIcon = {
            if (esPassword && onTogglePassword != null) {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = null,
                        tint = ColoresSaranta.Negro.copy(alpha = 0.5f)
                    )
                }
            }
        },
        singleLine = true,
        visualTransformation = if (esPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ColoresSaranta.VerdeOscuro,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedContainerColor = Color(0xFFF7F7F7),
            unfocusedContainerColor = Color(0xFFF7F7F7)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginExitoso = {}, onIrARegistro = {})
}