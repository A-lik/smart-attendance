package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.res.ResourcesCompat
import com.google.mlkit.vision.barcode.common.Barcode
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

data class DrawQrConfig(
    val density: Float,
    val overlayWidth: Float,
    val overlayHeight: Float,
    val paddingTop: Float = 110f * density,
    val paddingHorizontal: Float = 32f * density,
    val radius: Float = 8f
) {
    val boxWidth get() = overlayWidth - (paddingHorizontal * 2)
    private val centerX get() = overlayWidth / 2
    val leftRectF get() = (centerX - boxWidth / 2)
    val topRectF get() = paddingTop
    val rightRectF get() = (centerX + boxWidth / 2)
    val bottomRectF get() = paddingTop + boxWidth
    val bottomRectFWithoutPadding get() = bottomRectF - paddingHorizontal * 2

    companion object {
        fun DrawQrConfig.mapToRectF() = RectF(leftRectF, topRectF, rightRectF, bottomRectF)
        fun DrawQrConfig.mapToRect() = Rect(
            leftRectF.toInt(),
            topRectF.toInt(),
            rightRectF.toInt(),
            bottomRectFWithoutPadding.toInt()
        )

        fun Barcode.mapToRect(density: Float) = Rect(
            (boundingBox!!.left * density).toInt(),
            (boundingBox!!.top * density).toInt(),
            (boundingBox!!.right * density).toInt(),
            (boundingBox!!.bottom * density).toInt()
        )
    }
}

class DrawQrStyles(
    context: Context,
    density: Float
) {

    val scrimPaint = Paint().apply {
        color = Color(0x60000000)
    }

    val boxPaint: Paint = Paint().apply {
        color = SaColor.White.copy(0.9f)
        style = PaintingStyle.Stroke
        strokeWidth = 6f * density
        pathEffect = PathEffect.cornerPathEffect(8f)
        strokeCap = StrokeCap.Round
    }

    val eraserPaint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        blendMode = BlendMode.Clear
    }

    val textPaint = TextPaint().apply {
        isAntiAlias = true
        textSize = 16 * density
        color = SaColor.White.toArgb()
        typeface = ResourcesCompat.getFont(context, R.font.inter_medium)
    }
}