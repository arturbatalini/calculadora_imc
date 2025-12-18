package com.example.calculadoradeimc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calculadoradeimc.view.DetailScreen
import com.example.calculadoradeimc.view.HistoryScreen
import com.example.calculadoradeimc.view.Home

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    Home(
                        onNavigateToHistory = {
                            navController.navigate("history")
                        }
                    )
                }

                composable("history") {
                    HistoryScreen(
                        onBack = {
                            navController.popBackStack()
                        },
                        onNavigateToDetail = { recordId ->
                            navController.navigate("detail/$recordId")
                        }
                    )
                }

                composable(
                    route = "detail/{recordId}",
                    arguments = listOf(
                        navArgument("recordId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val recordId = backStackEntry.arguments?.getInt("recordId") ?: 0

                    DetailScreen(
                        recordId = recordId,
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}