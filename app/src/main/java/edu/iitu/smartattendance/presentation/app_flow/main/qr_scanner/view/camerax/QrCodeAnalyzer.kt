package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.content.Context
import android.content.res.Resources
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import edu.iitu.smartattendance.domain.profile.QrCode
import edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax.DrawQrConfig.Companion.mapToRect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QrCodeAnalyzer(
    private val context: Context,
    private val scope: CoroutineScope,
    private val callbacks: QrScannerHandlers
) : ImageAnalysis.Analyzer, OnFailureListener, OnSuccessListener<List<Barcode>>,
    StopBeforeNewScan by StopBeforeNewScanImpl(context) {

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    private val density = Resources.getSystem().displayMetrics.density
    private val squareRect by lazy {
        DrawQrConfig(
            density = density,
            overlayHeight = screenHeight.toFloat(),
            overlayWidth = screenWidth.toFloat()
        ).mapToRect()
    }

    private val scanner by lazy { BarcodeScanning.getClient(options) }
    private val options by lazy {
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    }


    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { img ->
            val iImage = InputImage
                .fromMediaImage(img, imageProxy.imageInfo.rotationDegrees)
            scanner.process(iImage)
                .addOnSuccessListener(this@QrCodeAnalyzer)
                .addOnFailureListener(this@QrCodeAnalyzer)
                .addOnCompleteListener { listOf(imageProxy, img).map { it.close() } }
        }
    }

    override fun onSuccess(barcodes: List<Barcode>?) {
        if (alreadyVibrating.value || barcodes == null) return
        if (!isQRCodeInsideSquare(barcodes)) return

        val qrCodes = barcodes.mapNotNull { it.rawValue }.map(QrCode::from)
        if (qrCodes.firstOrNull() == null) callbacks.handleError()
        else callbacks.handleSuccess(qrCodes.first()!!)
        scope.launch { qrScanned() }
    }

    private fun isQRCodeInsideSquare(barcodes: List<Barcode>): Boolean {
        if (barcodes.all { it.boundingBox == null || it.boundingBox!!.isEmpty }) return false
        val boundingBox = barcodes.first().mapToRect(density)
        return boundingBox.bottom <= squareRect.bottom && boundingBox.top >= squareRect.top
    }

    override fun onFailure(e: Exception) {
        e.printStackTrace()
    }
}

data class QrScannerHandlers(
    val handleSuccess: (QrCode) -> Unit,
    val handleError: () -> Unit
)