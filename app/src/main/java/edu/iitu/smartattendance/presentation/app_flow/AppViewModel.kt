package edu.iitu.smartattendance.presentation.app_flow

import androidx.lifecycle.ViewModel
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthExecutor
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.app_flow.auth.view.AuthViewModel
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeState
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeViewModel
import edu.iitu.smartattendance.presentation.common.app_navigation.AppConfig
import edu.iitu.smartattendance.presentation.common.app_navigation.MainConfig
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : ViewModel() {
    private val _appState = MutableStateFlow<AppConfig>(AppConfig.MainConfig)
    val appState: StateFlow<AppConfig> = _appState

    private val authExecutor: AuthExecutor = AuthExecutor(this)

    val authVm: AuthViewModel = AuthViewModel(authExecutor)
    val mainVm: MainViewModel = MainViewModel()

    fun switchToMainFlow() = _appState.tryEmit(AppConfig.MainConfig)

    fun switchToAuthFlow() = _appState.tryEmit(AppConfig.AuthConfig)

//    private fun observeAuthState() {
//        viewModelScope.launch {
//            authVm.state.collect {
//                if (it is AuthState.Success) _appState.value = AppState.MainFlow
//            }
//        }
//    }
}


class MainViewModel : ViewModel() {
    private val _mainState = MutableStateFlow<MainConfig>(MainConfig.HomeScreen)
    val mainState: StateFlow<MainConfig> = _mainState

    private val homeExecutor: HomeExecutor = HomeExecutor()
    private val homeViewModel: HomeViewModel = HomeViewModel(homeExecutor)

    fun getViewModel() = when (mainState.value) {
        MainConfig.HomeScreen -> homeViewModel
        MainConfig.ProfileScreen -> TODO()
    }
}

// Stopped at MainViewModel - composite of the ViewModels of screens inside MainFlow