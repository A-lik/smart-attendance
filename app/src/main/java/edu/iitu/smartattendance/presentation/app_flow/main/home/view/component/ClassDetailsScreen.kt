package edu.iitu.smartattendance.presentation.app_flow.main.home.view.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent

@Composable
fun ClassDetailsScreen(
    dispatch: (HomeEvent) -> Unit
) {
    BackHandler {
        dispatch(HomeEvent.DetailsEvent.NavigateBackClicked)
    }
    Column(modifier = Modifier
        .fillMaxSize()
//        .background(Color.Red)
    ) {

    }
}