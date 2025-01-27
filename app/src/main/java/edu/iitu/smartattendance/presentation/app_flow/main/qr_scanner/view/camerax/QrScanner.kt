package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.graphics.RectF
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax.DrawQrConfig.Companion.mapToRectF

//@Composable
//internal fun QrScanner(dispatch: (QrScannerEvent) -> Unit) {
//    val coroutineScope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val density = LocalDensity.current.density
//    val drawStyles = remember { DrawQrStyles(context, density) }
//    var buttonBounds by remember { mutableStateOf<RectF?>(RectF()) }
//    val navigationBarPadding =
//        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
//    val camera = remember {
//        CameraInit(
//            context = context,
//            scope = coroutineScope,
//            callbacks = QrScannerHandlers(
//                handleSuccess = { dispatch(QrScannerEvent.ScanningEvent.HandleResult(it)) },
//                handleError = {
//                    SnackbarEventBus.emitSnackbar(
//                        FidSnackBar.Informative(description = ResourceText.StringResource(R.string.qr_scanner_incorrect_qr))
//                    )
//                }
//            )
//        )
//    }
//    DisposableEffect(Unit) {
//        onDispose {
//            camera.onCleared()
//        }
//    }
//    AndroidView(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = navigationBarPadding)
//            .clickIfContains(buttonBounds) { dispatch(QrScannerEvent.ScanningEvent.NavigateToHomeScreen) }
//            .drawWithContent {
//                val rectF = DrawQrConfig(
//                    density = density,
//                    overlayHeight = size.height,
//                    overlayWidth = size.width,
//                ).mapToRectF()
//                drawIntoCanvas { canvas ->
//                    drawContent()
//                    drawStyles.eraserPaint.style = PaintingStyle.Fill
//                    canvas.drawRect(0f, 0f, size.width, size.height, drawStyles.scrimPaint)
//                    canvas.drawRoundedRect(rectF, drawStyles.eraserPaint)
//                    drawViewFinderBorder(canvas, rectF, drawStyles.boxPaint)
//                    drawViewFinderText(context, canvas, drawStyles.textPaint, density)
//                    buttonBounds = drawViewFinderButtonClose(context, canvas, density)
//                }
//            },
//        factory = { camera.initialize() }
//    )
//}