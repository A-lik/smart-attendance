package edu.iitu.smartattendance.presentation.app_flow.auth.model

import edu.iitu.smartattendance.presentation.app_flow.AppViewModel
import edu.iitu.smartattendance.presentation.common.mvi.BaseExecutor

class AuthExecutor (
    private val appVm: AppViewModel
) : BaseExecutor<AuthCommand, AuthEvent>() {
    override suspend fun execute(command: AuthCommand): AuthEvent? {
        return when (command) {
            AuthCommand.NavigateToMain -> appVm.switchToMainFlow().let { null }
        }
    }
}