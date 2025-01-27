package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.presentation.common.mvi.Transition
import edu.iitu.smartattendance.presentation.common.mvi.noTransition
import edu.iitu.smartattendance.presentation.common.mvi.toTransition

fun AuthState.EmailAuth.changeEmail(email: String) = copy(email = Email.from(email))
fun AuthState.EmailAuth.changePassword(password: CharArray) = copy(password = password)

fun AuthState.EmailAuth.isValid(): Boolean =
    email != null && password != null && password.toString().isNotBlank()

fun AuthState.EmailAuth.handleLogin(): Transition<AuthState, AuthCommand> =
    when {
        this.password == null -> this.noTransition()
        this.email != null -> AuthState.Loading(this).toTransition(
            AuthCommand.InitiateEmailLogin(
                email = this.email,
                password = this.password
            )
        )
        else -> this.noTransition()
    }