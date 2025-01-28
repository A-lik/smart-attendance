package edu.iitu.smartattendance.presentation.app_flow.main.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeState
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.component.ClassDetailsScreen
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.component.UpcomingClassesScreen
import edu.iitu.smartattendance.presentation.common.ui.component.appbar.SaBottomBar
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

@Composable
fun HomeScreen(
    state: HomeState,
    dispatch: (HomeEvent) -> Unit
) {
    val dispatchMemoized = remember { dispatch }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { SaBottomBar() }
    ) {
        innerPadding ->
        Column (
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .background(color = SaColor.DarkWhite)
        ) {
            when (state) {
                HomeState.LoadedDetails -> ClassDetailsScreen(dispatchMemoized)
                HomeState.LoadedClasses -> UpcomingClassesScreen(dispatchMemoized)
                HomeState.Loading -> TODO()
            }
        }
    }
}
