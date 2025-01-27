package edu.iitu.smartattendance.domain.auth.repo

import edu.iitu.smartattendance.domain.error.SaResult

interface AuthRepo {
    suspend fun loginWithCredentials(login: String, password: CharArray): SaResult<Unit>
}