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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestion_de_ervicios_turisticos.auth.ui.LoginScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.RegisterScreen
import com.example.gestion_de_ervicios_turisticos.auth.ui.WelcomeScreen
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
                        startDestination = "welcome",
                        modifier = Modifier.padding(innerPadding)
                    ) {
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
                            Greeting(name = "Android")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}