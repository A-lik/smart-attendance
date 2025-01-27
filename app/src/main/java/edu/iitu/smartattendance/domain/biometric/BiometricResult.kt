package edu.iitu.smartattendance.domain.biometric

import javax.crypto.Cipher

sealed interface BiometricResult{
    sealed interface AuthenticationError : BiometricResult {
        data object ErrorLockoutTemporarily : AuthenticationError
        data object ErrorLockoutPermanent : AuthenticationError
    }
    data object AuthenticationFailed : BiometricResult
    data class AuthenticationSuccess(val cipher: Cipher? = null) : BiometricResult
}