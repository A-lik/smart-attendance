package edu.iitu.smartattendance.infrastructure.network

import edu.iitu.smartattendance.domain.error.AppError
import edu.iitu.smartattendance.infrastructure.utils.Either
import edu.iitu.smartattendance.infrastructure.utils.toLeft
import edu.iitu.smartattendance.infrastructure.utils.toRight
import retrofit2.Response
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(
    call: suspend () -> Response<T>
): Either<AppError, T> {
    try {
        val response = call()

//        if (response.errorBody() != null) return AppError.InfrastructureError.UnknownError.toLeft()

        val body = response.body() ?: return AppError.InfrastructureError.UnknownError.toLeft()

        return body.toRight()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        return e.toAppError().toLeft()
    }
}

fun Exception.toAppError(): AppError =
    when (this) {
        is SocketTimeoutException -> AppError.InfrastructureError.NetworkError.TimeoutError
        is UnknownHostException, is ConnectException, is NoRouteToHostException ->
            AppError.InfrastructureError.NetworkError.ServiceUnavailable

        is SSLException -> AppError.InfrastructureError.NetworkError.ConnectionNotEstablished
        is MalformedURLException -> AppError.InfrastructureError.NetworkError.InvalidUrl
//        is ServerException -> AppError.DomainErrors.ValidationErrors(errors)
//        is ServerInterruption -> AppError.DomainErrors.SessionError(errors)
//        is FailedDependency -> AppError.InfrastructureError.NetworkError.FailedDependency
//        is ParsingException -> AppError.InfrastructureError.NetworkError.ParsingError
//        is CryptoException -> AppError.InfrastructureError.NetworkError.CryptoError
//        is NoConnectivityException -> AppError.InfrastructureError.NetworkError.NoInternetConnection
        else -> AppError.InfrastructureError.UnknownError
    }