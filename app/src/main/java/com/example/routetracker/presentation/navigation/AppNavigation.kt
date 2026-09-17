package com.example.routetracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.routetracker.presentation.detalleestacion.DetalleEstacionScreen
import com.example.routetracker.presentation.estaciones.EstacionesScreen
import com.example.routetracker.presentation.home.HomeScreen
import com.example.routetracker.presentation.login.LoginScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    val destinoInicial = if (FirebaseAuth.getInstance().currentUser != null) "home" else "login"

    NavHost(navController = navController, startDestination = destinoInicial) {

        composable("login") {
            LoginScreen(
                onLoginExitoso = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onLineaClick = { lineaId ->
                    navController.navigate("estaciones/$lineaId")
                }
            )
        }


        composable(
            route = "estaciones/{lineaId}",
            arguments = listOf(navArgument("lineaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val lineaId = backStackEntry.arguments?.getString("lineaId") ?: ""
            EstacionesScreen(
                lineaId = lineaId,
                onEstacionClick = { estacionId ->
                    navController.navigate("detalle/$lineaId/$estacionId")
                },
                onCambiarLinea = { nuevaLineaId ->
                    navController.navigate("estaciones/$nuevaLineaId") {
                        popUpTo("estaciones/{lineaId}") { inclusive = true }
                    }
                },
                onSalir = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") { popUpTo(0) { inclusive = true } }
                },
                onVolver = { navController.popBackStack() },
                onIrInicio = {
                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                }
            )
        }

        composable(
            route = "detalle/{lineaId}/{estacionId}",
            arguments = listOf(
                navArgument("lineaId") { type = NavType.StringType },
                navArgument("estacionId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lineaId = backStackEntry.arguments?.getString("lineaId") ?: ""
            val estacionId = backStackEntry.arguments?.getString("estacionId") ?: ""
            DetalleEstacionScreen(
                estacionId = estacionId,
                lineaId = lineaId,
                nombreEstacion = estacionId.replace("_", " ").replaceFirstChar { it.uppercase() },
                direcciones = listOf("Dirección 1", "Dirección 2"),
                onSalir = {
                    navController.navigate("home") { popUpTo(0) { inclusive = true } }
                },
                onIrInicio = {
                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                },
                onVolver = { navController.popBackStack() },
                onCambiarLinea = { nuevaLineaId ->
                    navController.navigate("estaciones/$nuevaLineaId") {
                        popUpTo("home")
                    }
                }
            )
        }
    }
}