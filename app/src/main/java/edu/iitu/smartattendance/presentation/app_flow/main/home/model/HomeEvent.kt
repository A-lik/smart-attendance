package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import android.content.Context
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine

sealed interface HomeEvent : StateMachine.Event {
    data object ViewClassDetailsClicked : HomeEvent

    sealed interface DetailsEvent : HomeEvent {
        data object NavigateBackClicked : DetailsEvent
        data class CheckInClicked(val context: Context) : DetailsEvent
    }
}