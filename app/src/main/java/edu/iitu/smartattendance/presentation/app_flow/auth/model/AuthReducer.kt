package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.presentation.common.mvi.StateMachine
import edu.iitu.smartattendance.presentation.common.mvi.Transition
import edu.iitu.smartattendance.presentation.common.mvi.noTransition
import edu.iitu.smartattendance.presentation.common.mvi.toTransition

object AuthReducer : StateMachine.Reducer<AuthState, AuthEvent, AuthCommand> {
    override fun reduce(state: AuthState, event: AuthEvent): Transition<AuthState, AuthCommand> {
        return when (state) {
            is AuthState.EmailAuth -> reduceEmailAuth(state, event)
            AuthState.Error -> TODO()
            AuthState.Loading -> TODO()
            AuthState.Success -> TODO()
        }
    }

    private fun reduceEmailAuth(
        state: AuthState.EmailAuth,
        event: AuthEvent
    ): Transition<AuthState, AuthCommand> = when (event) {
        is AuthEvent.PasswordChanged -> state.changePassword(event.password).toTransition()
        is AuthEvent.EmailedChanged -> state.changeEmail(event.email).toTransition()
        AuthEvent.LoginClicked -> state.toTransition(AuthCommand.NavigateToMain)
    }

}