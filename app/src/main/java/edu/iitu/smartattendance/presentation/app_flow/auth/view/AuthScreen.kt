package edu.iitu.smartattendance.presentation.app_flow.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthEvent
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.app_flow.auth.view.components.EmailAuthContent
import edu.iitu.smartattendance.presentation.app_flow.auth.view.components.authScreenGradient

@Composable
fun AuthWrapper(authVm: AuthViewModel) {
    val state = authVm.state.collectAsStateWithLifecycle()
    AuthScreen(state.value, authVm::dispatchEvent)
}

@Composable
fun AuthScreen(
//    modifier: Modifier = Modifier,
    state: AuthState,
    dispatch: (AuthEvent) -> Unit
) {
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
    val dispatchMemoized = remember { dispatch }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(authScreenGradient())
        .padding(vertical = systemBarPadding)
    ) {

        when (state) {
            is AuthState.EmailAuth -> EmailAuthContent(state, dispatchMemoized)
            AuthState.Error -> TODO()
            AuthState.Loading -> TODO()
            AuthState.Success -> TODO()
        }
    }

}