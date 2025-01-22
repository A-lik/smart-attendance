package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine

sealed interface AuthState : StateMachine.State {

    data class EmailAuth(
        val email: Email? = null,
        val password: CharArray? = null
    ) : AuthState

    data object Success : AuthState
    data object Error : AuthState
    data object Loading : AuthState
}