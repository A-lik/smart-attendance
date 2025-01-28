package edu.iitu.smartattendance.data.auth.repository

import edu.iitu.smartattendance.data.auth.AuthApi
import edu.iitu.smartattendance.data.auth.Credentials
import edu.iitu.smartattendance.domain.auth.repo.AuthRepo
import edu.iitu.smartattendance.domain.error.SaResult
import edu.iitu.smartattendance.infrastructure.network.safeApiCall
import edu.iitu.smartattendance.infrastructure.utils.toRight
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepoImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepo {
    override suspend fun loginWithCredentials(
        login: String,
        password: CharArray
    ): SaResult<Unit> {
        return safeApiCall {
            authApi.loginWithCredentials(Credentials(login, String(password)))
        }
    }

}