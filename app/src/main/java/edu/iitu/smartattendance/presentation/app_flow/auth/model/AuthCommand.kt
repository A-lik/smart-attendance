package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine


sealed interface AuthCommand : StateMachine.Command {
    data class InitiateEmailLogin(val email: Email, val password: CharArray) : AuthCommand
//    data object NavigateToMain : AuthCommand
}