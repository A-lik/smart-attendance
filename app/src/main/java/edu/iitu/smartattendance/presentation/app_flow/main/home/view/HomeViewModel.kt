package edu.iitu.smartattendance.presentation.app_flow.main.home.view

import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeCommand
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeReducer
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeState
import edu.iitu.smartattendance.presentation.common.mvi.BaseStore

class HomeViewModel (
    homeExecutor: HomeExecutor
) : BaseStore<HomeState, HomeEvent, HomeCommand>(
    config = Config(state = HomeState.LoadedHome),
    reducer = HomeReducer,
    executor = homeExecutor
) {
    init {
        update()
    }
}