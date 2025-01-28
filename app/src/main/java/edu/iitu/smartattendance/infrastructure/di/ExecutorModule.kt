package edu.iitu.smartattendance.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import edu.iitu.smartattendance.domain.auth.repo.AuthRepo
import edu.iitu.smartattendance.domain.biometric.BiometricPromptManager
import edu.iitu.smartattendance.domain.biometric.BiometricPromptManagerContract
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthExecutor
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeExecutor
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ExecutorModule {

    @ViewModelScoped
    @Provides
    fun provideAuthExecutor(
        authRepo: AuthRepo
    ) : AuthExecutor = AuthExecutor(authRepo)

    @ViewModelScoped
    @Provides
    fun provideHomeExecutor(biometricPromptManager: BiometricPromptManager) : HomeExecutor = HomeExecutor(biometricPromptManager)

    @Singleton
    @Provides
    fun provideBiometricPromptManager() : BiometricPromptManagerContract = BiometricPromptManager()
}