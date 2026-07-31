package com.example.gestion_de_ervicios_turisticos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestion_de_ervicios_turisticos.auth.ui.LoginScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.RegisterScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.WelcomeScreen
import com.example.gestion_de_ervicios_turisticos.home.ui.HomeScreen
import com.example.gestion_de_ervicios_turisticos.splash.ui.SplashConfig
import com.example.gestion_de_ervicios_turisticos.splash.ui.SplashScreen
import com.example.gestion_de_ervicios_turisticos.ui.theme.Gestion_de_ervicios_TuristicosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gestion_de_ervicios_TuristicosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash") {
                            SplashScreen(
                                onSplashFinished = {
                                    navController.navigate("welcome") {
                                        popUpTo("splash") { inclusive = true } // no se puede volver al splash con "atrás"
                                    }
                                },
                                config = SplashConfig(
                                    baseCircleSize = 160.dp,  // usa TUS valores finales aquí
                                    logoSize = 100.dp          // usa TUS valores finales aquí
                                )
                            )
                        }
                        composable("welcome") {
                            WelcomeScreen(
                                onIrALogin = { navController.navigate("login") },
                                onIrARegistro = { navController.navigate("register") }
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                onLoginExitoso = { navController.navigate("home") },
                                onIrARegistro = { navController.navigate("register") }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                onRegistroExitoso = { navController.navigate("home") },
                                onIrALogin = { navController.navigate("login") }
                            )
                        }
                        composable("home") {
                            HomeScreen(
                                onServicioClick = { servicio ->
                                    // TODO: cuando exista la pantalla de Detalle, navegar así:
                                    // navController.navigate("detalle/${servicio.id}")
                                },
                                onIrAItinerario = {
                                    // TODO: navegar cuando exista la pantalla de Itinerario
                                },
                                onIrAPerfil = {
                                    // TODO: navegar cuando exista la pantalla de Perfil
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
