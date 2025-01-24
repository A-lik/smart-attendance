package edu.iitu.smartattendance.presentation.app_flow.auth.view

import dagger.hilt.android.lifecycle.HiltViewModel
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthCommand
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthEvent
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthExecutor
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthReducer
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeViewModel
import edu.iitu.smartattendance.presentation.common.mvi.BaseStore
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    authExecutor: AuthExecutor
) : BaseStore<AuthState, AuthEvent, AuthCommand>(
    config = Config(AuthState.EmailAuth()),
    reducer = AuthReducer,
    executor = authExecutor
) {
    init {
        update()
    }
}