package edu.iitu.smartattendance.presentation.app_flow.main.home.view

import dagger.hilt.android.lifecycle.HiltViewModel
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeCommand
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeReducer
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeState
import edu.iitu.smartattendance.presentation.common.mvi.BaseStore
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeExecutor: HomeExecutor
) : BaseStore<HomeState, HomeEvent, HomeCommand>(
    config = Config(state = HomeState.LoadedClasses),
    reducer = HomeReducer,
    executor = homeExecutor
) {
    init {
        update()
    }
}