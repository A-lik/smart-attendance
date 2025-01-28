package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import android.content.Context
import edu.iitu.smartattendance.domain.biometric.isFingerPrintEnrolled
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine
import edu.iitu.smartattendance.presentation.common.mvi.Transition
import edu.iitu.smartattendance.presentation.common.mvi.noTransition
import edu.iitu.smartattendance.presentation.common.mvi.toTransition

object HomeReducer : StateMachine.Reducer<HomeState, HomeEvent, HomeCommand> {
    override fun reduce(state: HomeState, event: HomeEvent): Transition<HomeState, HomeCommand> {
        return when (state) {
            HomeState.LoadedDetails -> reduceLoadedDetails(state, event)
            HomeState.LoadedClasses -> reduceLoadedHome(state, event)
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
            HomeEvent.DetailsEvent.NavigateBackClicked -> HomeState.LoadedClasses.toTransition()
            is HomeEvent.DetailsEvent.CheckInClicked -> handleCheckIn(state, event.context)
        }
    }

    private fun handleCheckIn(state: HomeState, context: Context): Transition<HomeState, HomeCommand> =
        if (isFingerPrintEnrolled(context)) HomeCommand.InitiateCheckIn(context).toTransition(state)
    else state.noTransition()

}