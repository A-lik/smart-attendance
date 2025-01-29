package edu.iitu.smartattendance.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthScreen
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthViewModel
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeScreen
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeViewModel
import edu.iitu.smartattendance.presentation.app_flow.main.idk_yet.IdkYetScreen
import edu.iitu.smartattendance.presentation.app_flow.main.notification.NotificationScreen
import edu.iitu.smartattendance.presentation.app_flow.main.profile.ProfileScreen

@Composable
fun SaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
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
        composable<AppDestination.Notifications> {
            NotificationScreen()
        }
        composable<AppDestination.IdkYet> {
            IdkYetScreen()
        }
        composable<AppDestination.Profile> {
            ProfileScreen()
        }
    }
}