package edu.iitu.smartattendance.data.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun loginWithCredentials(@Body body: Credentials): Response<Unit>
}

data class Credentials(
    val email: String,
    val password: String
)