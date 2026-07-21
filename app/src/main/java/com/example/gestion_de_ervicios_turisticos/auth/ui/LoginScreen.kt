package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestion_de_ervicios_turisticos.auth.model.User
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.AuthUiState
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginExitoso: (User) -> Unit,
    onIrARegistro: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    // Cuando el login sea exitoso, navegamos automáticamente
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Exito) {
            onLoginExitoso((uiState as AuthUiState.Exito).usuario)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Iniciar sesión", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (uiState is AuthUiState.Error) {
            Text(
                text = (uiState as AuthUiState.Error).mensaje,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = { viewModel.iniciarSesion(correo, contrasena) },
            enabled = uiState !is AuthUiState.Cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is AuthUiState.Cargando) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text("Ingresar")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onIrARegistro) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}