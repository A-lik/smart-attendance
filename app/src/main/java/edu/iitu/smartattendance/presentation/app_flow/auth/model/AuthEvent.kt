package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine

sealed interface AuthEvent : StateMachine.Event {
    data class EmailedChanged(val email: String) : AuthEvent
    data class PasswordChanged(val password: CharArray) : AuthEvent
    data object LoginClicked : AuthEvent
}

// Left on writing State and Event for Auth