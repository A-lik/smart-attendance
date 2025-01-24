package edu.iitu.smartattendance.presentation.app_flow

import edu.iitu.smartattendance.presentation.common.navigation.AppDestination
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object NavEventBus {
    private val _navEvents = MutableSharedFlow<AppDestination>(extraBufferCapacity = 1)
    val navEvents: SharedFlow<AppDestination> = _navEvents.asSharedFlow()

    fun emitNavEvent(navEvent: AppDestination) = _navEvents.tryEmit(navEvent)
}