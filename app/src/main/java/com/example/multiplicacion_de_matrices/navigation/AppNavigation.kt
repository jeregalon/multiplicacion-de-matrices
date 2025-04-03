package com.example.multiplicacion_de_matrices.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multiplicacion_de_matrices.ui.screens.EqSys
import com.example.multiplicacion_de_matrices.ui.screens.Inverse
import com.example.multiplicacion_de_matrices.ui.screens.Menu
import com.example.multiplicacion_de_matrices.ui.screens.Multiply
import kotlinx.serialization.Serializable

@Serializable
object MenuScreen
@Serializable
object MultiplyScreen
@Serializable
object InverseScreen
@Serializable
object EqSysScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MenuScreen) {
        composable<MenuScreen> {
            Menu(
                onNavigateToMultiply = {
                    navController.navigate(
                        route = MultiplyScreen
                    )
                },
                onNavigateToInverse = {
                    navController.navigate(
                        route = InverseScreen
                    )
                },
                onNavigateToEqSys = {
                    navController.navigate(
                        route = EqSysScreen
                    )
                }
            )
        }
        composable<InverseScreen> {
            Inverse(
                onNavigateToMenu = {
                    navController.navigate(
                        route = MenuScreen
                    )
                }
            )
        }
        composable<MultiplyScreen> {
            Multiply(
                onNavigateToMenu = {
                    navController.navigate(
                        route = MenuScreen
                    )
                }
            )
        }
        composable<EqSysScreen> {
            EqSys(
                onNavigateToMenu = {
                    navController.navigate(
                        route = MenuScreen
                    )
                }
            )
        }
    }
}