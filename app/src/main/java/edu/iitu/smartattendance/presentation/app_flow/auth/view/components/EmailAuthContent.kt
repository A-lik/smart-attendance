package edu.iitu.smartattendance.presentation.app_flow.auth.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthEvent
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun EmailAuthContent(
    state: AuthState.EmailAuth,
    dispatch: (AuthEvent) -> Unit
) {
    Column(
        modifier = Modifier
//            .background(brush = authScreenGradient())
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.ime),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = stringResource(id = R.string.welcome),
            color = SaColor.White,
            style = SaTheme.typography.titleBold32
        )

        Spacer(modifier = Modifier.height(50.dp))

        LoginCard(state, dispatch)
    }
}

@Composable
fun authScreenGradient() = Brush.verticalGradient(
    colors = listOf(SaColor.Primary400, SaColor.Primary200)
)

