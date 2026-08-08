package com.example.gestion_de_ervicios_turisticos.auth.data

import androidx.compose.runtime.mutableStateOf
import com.example.gestion_de_ervicios_turisticos.auth.model.User

/**
 * Guarda en memoria el usuario que inició sesión actualmente.
 * Simple a propósito: no persiste si cierras la app (eso vendría después,
 * cuando haya backend real con JWT y se pueda usar DataStore o similar).
 */
object SesionUsuario {

    var usuarioActual = mutableStateOf<User?>(null)
        private set

    var token = mutableStateOf<String?>(null)
        private set

    fun iniciarSesion(
        user: User,
        token: String
    ) {
        usuarioActual.value = user
        this.token.value = token
    }

    fun cerrarSesion() {
        usuarioActual.value = null
        token.value = null
    }

    fun actualizarUsuario(usuarioActualizado: User) {
        usuarioActual.value = usuarioActualizado
    }
}