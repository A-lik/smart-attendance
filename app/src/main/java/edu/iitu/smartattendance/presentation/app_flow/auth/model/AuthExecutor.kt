package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.common.mvi.BaseExecutor
import edu.iitu.smartattendance.presentation.common.navigation.AppDestination
import javax.inject.Inject

data class AuthCallbacks(
    val navigateToHome: () -> Unit = {
        NavEventBus.emitNavEvent(AppDestination.Home)
    }
)

class AuthExecutor (
    private val callbacks: AuthCallbacks = AuthCallbacks()
) : BaseExecutor<AuthCommand, AuthEvent>() {
    override suspend fun execute(command: AuthCommand): AuthEvent? =
        when (command) {
            AuthCommand.NavigateToMain -> callbacks.navigateToHome().let { null }
        }
}