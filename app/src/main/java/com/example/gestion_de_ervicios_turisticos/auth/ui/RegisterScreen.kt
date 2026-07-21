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
import com.example.gestion_de_ervicios_turisticos.auth.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    onRegistroExitoso: (User) -> Unit,
    onIrALogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Exito) {
            onRegistroExitoso((uiState as AuthUiState.Exito).usuario)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Crear cuenta", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

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
            onClick = { viewModel.registrar(nombre, correo, contrasena) },
            enabled = uiState !is AuthUiState.Cargando,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState is AuthUiState.Cargando) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text("Registrarme")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onIrALogin) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}