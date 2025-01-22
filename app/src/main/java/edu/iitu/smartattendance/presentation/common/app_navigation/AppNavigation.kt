package edu.iitu.smartattendance.presentation.common.app_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.iitu.smartattendance.presentation.app_flow.AppViewModel
import edu.iitu.smartattendance.presentation.app_flow.MainViewModel
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthWrapper
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeScreen
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeWrapper
import edu.iitu.smartattendance.presentation.common.ui.component.appbar.RenderSaBottomBar
import edu.iitu.smartattendance.presentation.common.ui.component.saBackground

@Composable
fun AppNavigation(appVm: AppViewModel) {

    val state by appVm.appState.collectAsStateWithLifecycle()
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()

    Box(modifier = Modifier
        .fillMaxSize()
        .saBackground(state)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = { RenderSaBottomBar(state) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(vertical = systemBarPadding)
            ) {
                when (state) {
                    is AppConfig.AuthConfig -> AuthWrapper(appVm.authVm)
                    is AppConfig.MainConfig -> MainNavigation(appVm.mainVm)
                }
            }
        }
    }
}

@Composable
private fun MainNavigation(mainViewModel: MainViewModel) {
    val state by mainViewModel.mainState.collectAsState()

    when (state) {
        MainConfig.HomeScreen -> HomeWrapper(viewModel = mainViewModel.getViewModel())
        MainConfig.ProfileScreen -> TODO()
    }
}

sealed interface AppConfig {
    data object AuthConfig : AppConfig
    data object MainConfig : AppConfig
}

sealed interface MainConfig {
    data object HomeScreen : MainConfig
    data object ProfileScreen : MainConfig
}