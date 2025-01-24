package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import edu.iitu.smartattendance.presentation.common.mvi.BaseExecutor
import javax.inject.Inject

class HomeExecutor : BaseExecutor<HomeCommand, HomeEvent>() {
    override suspend fun execute(command: HomeCommand): HomeEvent? {
        TODO("Not yet implemented")
    }

}