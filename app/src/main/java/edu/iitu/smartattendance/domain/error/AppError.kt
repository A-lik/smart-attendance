package edu.iitu.smartattendance.domain.error

import edu.iitu.smartattendance.infrastructure.utils.Either

typealias SaResult<T> = Either<AppError, T>

sealed interface AppError {
    sealed interface InfrastructureError : AppError {

        sealed interface NetworkError : InfrastructureError {
            data object TimeoutError : NetworkError
            data object ServiceUnavailable : NetworkError
            data object InvalidUrl : NetworkError
            data object ConnectionNotEstablished : NetworkError
            data object NoInternetConnection : NetworkError
            data object FailedDependency : AppError
            data object ParsingError : AppError
            data object CryptoError : AppError
        }
        data object EncryptionError : InfrastructureError
        data object SoIntegrityError : InfrastructureError
        data object ParsingError : InfrastructureError
        data object UnknownError : InfrastructureError
        data object DataProcessingError : InfrastructureError
        data object AccountDataError : InfrastructureError
    }
}