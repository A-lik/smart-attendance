package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.presentation.common.mvi.StateMachine


sealed interface AuthCommand : StateMachine.Command {
//    data class EmailLogin(val email: Email, val password: CharArray)
    data object NavigateToMain : AuthCommand
}