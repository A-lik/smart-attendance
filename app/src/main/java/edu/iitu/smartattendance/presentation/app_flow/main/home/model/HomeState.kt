package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import edu.iitu.smartattendance.presentation.common.mvi.StateMachine

sealed interface HomeState : StateMachine.State {
    data object LoadedHome : HomeState
    data object LoadedDetails : HomeState
    data object Loading : HomeState
}