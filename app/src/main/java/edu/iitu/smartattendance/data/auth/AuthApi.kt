package edu.iitu.smartattendance.data.auth

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun loginWithCredentials(@Body body: Credentials): Response<Unit>
}

data class Credentials(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)