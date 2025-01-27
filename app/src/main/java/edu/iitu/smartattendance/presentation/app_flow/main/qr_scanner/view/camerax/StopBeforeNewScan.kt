package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface StopBeforeNewScan {
    val alreadyVibrating: StateFlow<Boolean>
    suspend fun qrScanned()
}

class StopBeforeNewScanImpl(context: Context) : StopBeforeNewScan {
    private val vibrator by lazy { context.getSystemService(Vibrator::class.java) }
    override val alreadyVibrating = MutableStateFlow(false)

    override suspend fun qrScanned() {
        vibrate()
        alreadyVibrating.emit(true)
        delay(2000)
        alreadyVibrating.emit(false)
    }

    private fun vibrate() {
        if (!vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 100))
        } else {
            vibrator.vibrate(100)
        }
    }
}
