package edu.iitu.smartattendance.presentation.app_flow.auth.model

import android.util.Log
import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.domain.auth.repo.AuthRepo
import edu.iitu.smartattendance.infrastructure.utils.Either
import edu.iitu.smartattendance.infrastructure.utils.toLeft
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.common.mvi.BaseExecutor
import edu.iitu.smartattendance.presentation.common.navigation.AppDestination
import javax.inject.Inject

data class AuthCallbacks(
    val navigateToHome: () -> Unit = {
        NavEventBus.emitNavEvent(AppDestination.Home)
    }
)

class AuthExecutor @Inject constructor(
    private val authRepo: AuthRepo,
    private val callbacks: AuthCallbacks = AuthCallbacks()
) : BaseExecutor<AuthCommand, AuthEvent>() {
    override suspend fun execute(command: AuthCommand): AuthEvent? =
        when (command) {
//            AuthCommand.NavigateToMain -> callbacks.navigateToHome().let { null }
            is AuthCommand.InitiateEmailLogin -> {
//                callbacks.navigateToHome().let { null }
                loginEmail(command.email, command.password)
            }
        }

    private suspend fun loginEmail(
        email: Email,
        password: CharArray
    ): AuthEvent? =
        when (val result = authRepo.loginWithCredentials(email.value, password)) {
            is Either.Left -> null
            is Either.Right -> callbacks.navigateToHome().let { null }
        }
}