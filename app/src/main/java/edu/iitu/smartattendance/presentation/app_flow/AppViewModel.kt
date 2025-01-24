package edu.iitu.smartattendance.presentation.app_flow

import androidx.lifecycle.ViewModel
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.view.HomeViewModel
//import edu.iitu.smartattendance.presentation.common.app_navigation.MainConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//class AppViewModel : ViewModel() {
//    private val _appState = MutableStateFlow<AppConfig>(AppConfig)
//    val appState: StateFlow<AppConfig> = _appState
//
//    private val authExecutor: AuthExecutor = AuthExecutor(this)
//
//    val authVm: AuthViewModel = AuthViewModel(authExecutor)
//    val mainVm: MainViewModel = MainViewModel()
//
//    fun emit(state: AppConfig) = _appState.tryEmit(state)
//
//    fun switchToMainFlow() = _appState.tryEmit(AppConfig.MainConfig.HomeConfig)
//
//    fun switchToAuthFlow() = _appState.tryEmit(AppConfig.AuthConfig)
//
////    private fun observeAuthState() {
////        viewModelScope.launch {
////            authVm.state.collect {
////                if (it is AuthState.Success) _appState.value = AppState.MainFlow
////            }
////        }
////    }
//}


class MainViewModel : ViewModel() {
//    private val _mainState = MutableStateFlow<MainConfig>(MainConfig.HomeScreen)
//    val mainState: StateFlow<MainConfig> = _mainState

    private val homeExecutor: HomeExecutor = HomeExecutor()
    val homeViewModel: HomeViewModel = HomeViewModel(homeExecutor)

//    fun getViewModel() = when (mainState.value) {
//        MainConfig.HomeScreen -> homeViewModel
//        MainConfig.ProfileScreen -> TODO()
//        AppConfig.MainConfig.DetailsScreen -> TODO()
//        AppConfig.MainConfig.HomeScreen -> TODO()
//    }
}

// Stopped at MainViewModel - composite of the ViewModels of screens inside MainFlow