package edu.iitu.smartattendance.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus

@Composable
fun AppNav(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        NavEventBus.navEvents.collect { handleNavEvent(navController, it) }
    }

    SaNavHost(
//        modifier = modifier.consumeWindowInsets(),
        navController = navController,
        startDestination = AppDestination.Home
    )
}

private fun handleNavEvent(navController: NavHostController, destination: AppDestination) =
    navController.navigate(destination) {
        if (destination is AppDestination.Home)
            popUpTo(AppDestination.Auth) { inclusive = true }
    }