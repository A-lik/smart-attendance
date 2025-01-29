package edu.iitu.smartattendance.presentation.common.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.common.ui.component.appbar.AnimatedSaBottomBar

@Composable
fun AppNav(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val isBottomBarHidden = currentBackStackEntry?.destination?.hierarchy?.any(::hasNotBottomBar) ?: true

    LaunchedEffect(Unit) {
        NavEventBus.navEvents.collect { handleNavEvent(navController, it) }
    }

    Scaffold(
        bottomBar = {
            AnimatedSaBottomBar(visible = !isBottomBarHidden)
        }
    ) { innerPadding ->
        SaNavHost(
            modifier = modifier.consumeWindowInsets(innerPadding),
            navController = navController,
            startDestination = AppDestination.Auth
        )
    }
}

private fun hasNotBottomBar(destination: NavDestination): Boolean {
    val routesWithNoBottomBar = setOf(AppDestination.Auth)
    return routesWithNoBottomBar.any { destination.hasRoute(it::class) }
}

private fun handleNavEvent(navController: NavHostController, destination: AppDestination) =
    navController.navigate(destination) {
        popUpTo(0) { inclusive = true }
    }