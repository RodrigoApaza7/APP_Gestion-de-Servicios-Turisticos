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
import com.example.gestion_de_ervicios_turisticos.auth.model.RolUsuario
import com.example.gestion_de_ervicios_turisticos.auth.ui.EditarPerfilScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.LoginScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.RegisterScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.SeleccionRolScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.WelcomeScreen
import com.example.gestion_de_ervicios_turisticos.home.ui.BusquedaScreen
import com.example.gestion_de_ervicios_turisticos.home.ui.DetalleServicioScreen
import com.example.gestion_de_ervicios_turisticos.home.ui.HomeScreen
import com.example.gestion_de_ervicios_turisticos.itinerario.ui.ItinerarioScreen
import com.example.gestion_de_ervicios_turisticos.notificaciones.ui.NotificacionesScreen
import com.example.gestion_de_ervicios_turisticos.perfil.ui.PerfilScreen
import com.example.gestion_de_ervicios_turisticos.prestador.ui.AgregarServicioScreen
import com.example.gestion_de_ervicios_turisticos.prestador.ui.PrestadorDashboardScreen
import com.example.gestion_de_ervicios_turisticos.reservas.ui.HistorialReservasScreen
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
                                onIrARegistro = { navController.navigate("seleccion-rol") } // <- antes decía "register"
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                onLoginExitoso = { usuario ->
                                    if (usuario.rol == "prestador") {
                                        navController.navigate("home-prestador") { popUpTo("login") { inclusive = true } }
                                    } else {
                                        navController.navigate("home") { popUpTo("login") { inclusive = true } }
                                    }
                                },
                                onIrARegistro = { navController.navigate("seleccion-rol") }
                            )
                        }
                        composable("register/{rol}") { backStackEntry ->
                            val rolValor = backStackEntry.arguments?.getString("rol") ?: "turista"
                            val rol = RolUsuario.entries.find { it.valor == rolValor } ?: RolUsuario.TURISTA

                            RegisterScreen(
                                rol = rol,
                                onRegistroExitoso = { usuario ->
                                    if (usuario.rol == "prestador") {
                                        navController.navigate("home-prestador") { popUpTo("welcome") { inclusive = true } }
                                    } else {
                                        navController.navigate("home") { popUpTo("welcome") { inclusive = true } }
                                    }
                                },
                                onIrALogin = { navController.navigate("login") }
                            )
                        }
                        composable("home") {
                            HomeScreen(
                                onServicioClick = { servicio -> navController.navigate("detalle/${servicio.id}") },
                                onIrAItinerario = { navController.navigate("itinerario") },
                                onIrABuscar = { navController.navigate("busqueda") },
                                onIrAPerfil = { navController.navigate("perfil") },
                                onIrANotificaciones = { navController.navigate("notificaciones") }
                            )
                        }
                        composable("detalle/{servicioId}") { backStackEntry ->
                            val servicioId = backStackEntry.arguments?.getString("servicioId") ?: ""
                            DetalleServicioScreen(
                                servicioId = servicioId,
                                onVolver = { navController.popBackStack() },
                                onReservar = { servicio ->
                                    // TODO: conectar cuando exista la lógica de Reservas
                                }
                            )
                        }
                        composable("perfil") {
                            PerfilScreen(
                                onIrAHistorial = { navController.navigate("historial") },
                                onIrAEditarPerfil = { navController.navigate("editar-perfil") },
                                onCerrarSesion = {
                                    navController.navigate("welcome") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("historial") {
                            HistorialReservasScreen(
                                onVolver = { navController.popBackStack() }
                            )
                        }
                        composable("itinerario") {
                            ItinerarioScreen(
                                onConfirmarItinerario = {
                                    // TODO: conectar cuando exista la lógica real de confirmar/pagar
                                }
                            )
                        }
                        composable("seleccion-rol") {
                            SeleccionRolScreen(
                                onRolSeleccionado = { rol ->
                                    navController.navigate("register/${rol.valor}")
                                }
                            )
                        }
                        composable("editar-perfil") {
                            EditarPerfilScreen(
                                onVolver = { navController.popBackStack() },
                                onGuardadoExitoso = { navController.popBackStack() }
                            )
                        }
                        composable("busqueda") {
                            BusquedaScreen(
                                onVolver = { navController.popBackStack() },
                                onServicioClick = { servicio -> navController.navigate("detalle/${servicio.id}") }
                            )
                        }
                        composable("notificaciones") {
                            NotificacionesScreen(onVolver = { navController.popBackStack() })
                        }
                        composable("home-prestador") {
                            PrestadorDashboardScreen(
                                onAgregarServicio = { navController.navigate("agregar-servicio") },
                                onVerServicio = { servicio -> navController.navigate("detalle/${servicio.id}") },
                                onIrAPerfil = { navController.navigate("perfil") }
                            )
                        }
                        composable("agregar-servicio") {
                            AgregarServicioScreen(
                                onVolver = { navController.popBackStack() },
                                onGuardadoExitoso = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
