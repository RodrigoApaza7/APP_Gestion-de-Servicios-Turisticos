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

    fun iniciarSesion(user: User) {
        usuarioActual.value = user
    }

    fun cerrarSesion() {
        usuarioActual.value = null
    }
}