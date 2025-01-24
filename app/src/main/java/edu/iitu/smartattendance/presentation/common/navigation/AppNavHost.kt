package edu.iitu.smartattendance.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthScreen
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthViewModel
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthWrapper
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeScreen
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        NavEventBus.navEvents.collect { handleNavEvent(navController, it) }
    }
    
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.Home,
    ) {
        composable<AppDestination.Auth> {
            val viewModel = hiltViewModel<AuthViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            AuthScreen(state.value, viewModel::dispatchEvent)
        }
        composable<AppDestination.Home> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle().value
            HomeScreen(state = state, viewModel::dispatchEvent)
        }
    }
}

private fun handleNavEvent(navController: NavHostController, destination: AppDestination) =
    navController.navigate(destination) {
        if (destination is AppDestination.Home)
            popUpTo(AppDestination.Auth) { inclusive = true }
    }