package com.example.gestion_de_ervicios_turisticos.auth.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gestion_de_ervicios_turisticos.ui.theme.ColoresSaranta

// Campo reutilizable con ícono a la izquierda, estilo del mockup (tarjeta blanca, borde redondeado).
// mostrarCheck=true dibuja un check verde a la derecha cuando el campo ya tiene contenido válido.
@Composable
fun CampoConIcono(
    valor: String,
    onCambio: (String) -> Unit,
    placeholder: String,
    icono: ImageVector,
    interactionSource: MutableInteractionSource? = null,
    esPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePassword: (() -> Unit)? = null,
    mostrarCheck: Boolean = false,
    completado: Boolean = false,
    teclado: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        placeholder = { Text(placeholder, color = ColoresSaranta.Negro.copy(alpha = 0.4f)) },
        leadingIcon = { Icon(icono, contentDescription = null, tint = ColoresSaranta.VerdeOscuro) },
        trailingIcon = {
            when {
                esPassword && onTogglePassword != null -> {
                    IconButton(onClick = onTogglePassword) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = ColoresSaranta.Negro.copy(alpha = 0.5f)
                        )
                    }
                }
                mostrarCheck && completado -> {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = ColoresSaranta.Verde)
                }
            }
        },
        singleLine = true,
        visualTransformation = if (esPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = teclado),
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