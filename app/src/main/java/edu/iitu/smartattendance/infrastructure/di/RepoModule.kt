package edu.iitu.smartattendance.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.iitu.smartattendance.data.auth.AuthApi
import edu.iitu.smartattendance.data.auth.repository.AuthRepoImpl
import edu.iitu.smartattendance.domain.auth.repo.AuthRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideAuthRepo(authApi: AuthApi): AuthRepo {
        return AuthRepoImpl(authApi)
    }
}