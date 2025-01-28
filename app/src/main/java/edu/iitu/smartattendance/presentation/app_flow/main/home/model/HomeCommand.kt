package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import android.content.Context
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine

sealed interface HomeCommand : StateMachine.Command {
//    data object ViewClassDetails : HomeCommand
    data class InitiateCheckIn(val context: Context) : HomeCommand
}