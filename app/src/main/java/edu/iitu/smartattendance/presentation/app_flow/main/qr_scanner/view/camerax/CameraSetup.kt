package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class CameraInit(
    private val context: Context,
    private val scope: CoroutineScope,
    callbacks: QrScannerHandlers
) : Runnable {

    private val cameraExecutor by lazy { Executors.newSingleThreadExecutor() }
    private val defaultExecutor by lazy { ContextCompat.getMainExecutor(context) }
    private val cameraProviderFuture by lazy { ProcessCameraProvider.getInstance(context) }
    private val cameraProvider: ProcessCameraProvider by lazy { cameraProviderFuture.get() }
    private val imageCapture by lazy { ImageCapture.Builder().build() }

    private val previewView by lazy {
        PreviewView(context).also {
            it.scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    private val preview by lazy {
        Preview.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
            .also { it.setSurfaceProvider(previewView.surfaceProvider) }
    }

    private val imageAnalyzer by lazy {
        ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also { it.setAnalyzer(cameraExecutor, QrCodeAnalyzer(context, scope, callbacks)) }
    }

    fun onCleared() {
        cameraExecutor.shutdown()
        cameraProvider.unbindAll()
    }

    fun initialize(): PreviewView {
        cameraProviderFuture.addListener(this, defaultExecutor)
        return previewView
    }

    override fun run() {
        try {
            scope.launch {
                cameraProvider.unbindAll()
                delay(1000)
                cameraProvider.bindToLifecycle(
                    context as ComponentActivity,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    imageCapture,
                    imageAnalyzer
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
