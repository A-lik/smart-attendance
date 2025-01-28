package edu.iitu.smartattendance.presentation.app_flow.main.home.model

import android.content.Context
import edu.iitu.smartattendance.domain.biometric.BiometricPromptManagerContract
import edu.iitu.smartattendance.domain.biometric.BiometricResult
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.common.mvi.BaseExecutor
import edu.iitu.smartattendance.presentation.common.navigation.AppDestination
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeExecutor @Inject constructor(
    private val biometricPrompt: BiometricPromptManagerContract
) : BaseExecutor<HomeCommand, HomeEvent>() {
    override suspend fun execute(command: HomeCommand): HomeEvent? {
        return when (command) {
            is HomeCommand.InitiateCheckIn -> showBiometricPrompt(command.context).let { null }
//            HomeCommand.ViewClassDetails -> TODO()
        }
    }

    private fun showBiometricPrompt(context: Context) {
        biometricPrompt.showBiometricPrompt(context)
        scope?.launch {
            biometricPrompt.promptResults.collect { collectBiometricResult(it) }
        }
    }

    private fun collectBiometricResult(result: BiometricResult) = when (result) {
        is BiometricResult.AuthenticationSuccess -> NavEventBus.emitNavEvent(AppDestination.Auth).let { null }
        else -> Unit
    }

}