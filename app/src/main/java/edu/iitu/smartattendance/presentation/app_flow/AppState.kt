package edu.iitu.smartattendance.presentation.app_flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object AppStateBus {
    private val _state = MutableSharedFlow<AppState>(extraBufferCapacity = 1)
    val state = _state.asSharedFlow()

    fun emit(state: AppState) = _state.tryEmit(state)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun reset() = _state.resetReplayCache()
}

sealed interface AppState {
    data object Authorization : AppState
    data object MainFlow : AppState
}