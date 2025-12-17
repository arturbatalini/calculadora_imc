package com.example.calculadoradeimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadoradeimc.view.HistoryScreen
import com.example.calculadoradeimc.view.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // 1. Cria o controlador de navegação
            val navController = rememberNavController()

            // 2. Define as rotas (Home -> Histórico)
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                // Rota da Tela Inicial
                composable("home") {
                    Home(
                        onNavigateToHistory = {
                            navController.navigate("history")
                        }
                    )
                }

                // Rota da Tela de Histórico
                composable("history") {
                    HistoryScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}