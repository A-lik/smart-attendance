package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import edu.iitu.smartattendance.presentation.common.mvi.StateMachine
import edu.iitu.smartattendance.presentation.common.mvi.Transition
import edu.iitu.smartattendance.presentation.common.mvi.noTransition
import edu.iitu.smartattendance.presentation.common.mvi.toTransition

object HomeReducer : StateMachine.Reducer<HomeState, HomeEvent, HomeCommand> {
    override fun reduce(state: HomeState, event: HomeEvent): Transition<HomeState, HomeCommand> {
        return when (state) {
            HomeState.LoadedDetails -> reduceLoadedDetails(state, event)
            HomeState.LoadedHome -> reduceLoadedHome(state, event)
            HomeState.Loading -> TODO()
        }
    }

    private fun reduceLoadedHome(
        state: HomeState,
        event: HomeEvent
    ) : Transition<HomeState, HomeCommand> = when (event) {
        HomeEvent.ViewClassDetailsClicked -> HomeState.LoadedDetails.toTransition()
        else -> state.noTransition()
    }

    private fun reduceLoadedDetails(
        state: HomeState,
        event: HomeEvent
    ) : Transition<HomeState, HomeCommand> {
        if (event !is HomeEvent.DetailsEvent) return state.noTransition()

        return when (event) {
            HomeEvent.DetailsEvent.NavigateBackClicked -> HomeState.LoadedHome.toTransition()
        }
    }

}