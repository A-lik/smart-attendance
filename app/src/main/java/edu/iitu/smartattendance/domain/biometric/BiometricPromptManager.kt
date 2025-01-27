package edu.iitu.smartattendance.domain.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.fragment.app.FragmentActivity
import edu.iitu.smartattendance.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.crypto.Cipher
import javax.inject.Inject

interface BiometricPromptManagerContract {
    val promptResults: Flow<BiometricResult>
    fun showBiometricPrompt(context: Context, cipher: Cipher? = null)
}

class BiometricPromptManager @Inject constructor():
    BiometricPromptManagerContract,
    BiometricPrompt.AuthenticationCallback() {

    private val resultChannel = Channel<BiometricResult>()

    private fun sendBiometricResult(biometricResult: BiometricResult) =
        resultChannel.trySend(biometricResult)

    override val promptResults: Flow<BiometricResult>
        get() = resultChannel.receiveAsFlow()

    override fun showBiometricPrompt(context: Context, cipher: Cipher?) {
        if (context !is FragmentActivity) throw IllegalArgumentException("context must be either FragmentActivity or Fragment")
        val promptInfo = PromptInfo.Builder()
            .setTitle(context.getString(R.string.app_name))
            .setSubtitle(context.getString(R.string.biometric_subtitle))
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setNegativeButtonText(context.getString(R.string.biometric_cancel))
            .build()

        val prompt = BiometricPrompt(context, this)
        if (cipher != null) {
            prompt.authenticate(promptInfo, BiometricPrompt.CryptoObject(cipher))
        } else prompt.authenticate(promptInfo)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        val cipher = result.cryptoObject?.cipher
        sendBiometricResult(BiometricResult.AuthenticationSuccess(cipher))
//        resultChannel.trySend(BiometricResult.AuthenticationSuccess(cipher))
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        sendBiometricResult(BiometricResult.AuthenticationFailed)
//        resultChannel.trySend(BiometricResult.AuthenticationFailed)
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        super.onAuthenticationError(errorCode, errString)

        when (errorCode) {
            BiometricPrompt.ERROR_LOCKOUT -> {
                sendBiometricResult(BiometricResult.AuthenticationError.ErrorLockoutTemporarily)
//                resultChannel.trySend(BiometricResult.AuthenticationError.ErrorLockoutTemporarily)
            }

            BiometricPrompt.ERROR_LOCKOUT_PERMANENT -> {
                sendBiometricResult(BiometricResult.AuthenticationError.ErrorLockoutPermanent)
//                resultChannel.trySend(BiometricResult.AuthenticationError.ErrorLockoutPermanent)
            }
        }
    }
}

fun isFingerPrintEnrolled(context: Context): Boolean {
    if (context !is FragmentActivity) throw IllegalArgumentException("context must be either FragmentActivity or Fragment")
    val biometricManager = BiometricManager.from(context)
    return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
}