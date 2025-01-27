package edu.iitu.smartattendance.presentation.app_flow.main.qr_scanner.view.camerax

import android.content.Context
import android.graphics.Bitmap
import android.graphics.RectF
import android.text.TextPaint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import edu.iitu.smartattendance.R

internal fun DrawScope.drawViewFinderBorder(canvas: Canvas, rectF: RectF, boxPaint: Paint) {
    val mBorderLineLength = 30.dp.toPx()
    val path = Path()
    path.moveTo(rectF.left, (rectF.top + mBorderLineLength))
    path.lineTo(rectF.left, rectF.top)
    path.lineTo((rectF.left + mBorderLineLength), rectF.top)
    canvas.drawPath(path, boxPaint)

    path.moveTo(rectF.right, (rectF.top + mBorderLineLength))
    path.lineTo(rectF.right, rectF.top)
    path.lineTo((rectF.right - mBorderLineLength), rectF.top)
    canvas.drawPath(path, boxPaint)

    path.moveTo(rectF.right, (rectF.bottom - mBorderLineLength))
    path.lineTo(rectF.right, rectF.bottom)
    path.lineTo((rectF.right - mBorderLineLength), rectF.bottom)
    canvas.drawPath(path, boxPaint)

    path.moveTo(rectF.left, (rectF.bottom - mBorderLineLength))
    path.lineTo(rectF.left, rectF.bottom)
    path.lineTo((rectF.left + mBorderLineLength), rectF.bottom)
    canvas.drawPath(path, boxPaint)
    canvas.drawPath(path, boxPaint)
}

internal fun DrawScope.drawViewFinderText(
    context: Context,
    canvas: Canvas,
    textPaint: TextPaint,
    density: Float
) {
    val bottomText = context.getString(R.string.scan_qr)
    val textWidth = textPaint.measureText(bottomText)
    val textX = (size.width - textWidth) / 2
    val textY = size.height - 140 * density
    canvas.nativeCanvas.drawText(bottomText, textX, textY, textPaint)
}

internal fun DrawScope.drawViewFinderButtonClose(
    context: Context,
    canvas: Canvas,
    density: Float
): RectF? {
    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_close_button) ?: return null
    val buttonWidth = drawable.intrinsicWidth.toFloat()
    val buttonHeight = drawable.intrinsicHeight.toFloat()
    val xPos = (size.width - buttonWidth) / 2
    val yPos = size.height - buttonHeight - 60 * density
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val bitmapCanvas = android.graphics.Canvas(bitmap)
    drawable.setBounds(0, 0, bitmapCanvas.width, bitmapCanvas.height)
    drawable.draw(bitmapCanvas)

    canvas.nativeCanvas.drawBitmap(bitmap, xPos, yPos, null)
    return RectF(xPos, yPos, xPos + buttonWidth, yPos + buttonHeight)
}

internal fun Canvas.drawRoundedRect(rectF: RectF, eraserPaint: Paint) = drawRoundRect(
    left = rectF.left,
    top = rectF.top,
    right = rectF.right,
    bottom = rectF.bottom,
    8f,
    8f,
    eraserPaint
)

internal fun Modifier.clickIfContains(coordinates: RectF?, click: () -> Unit): Modifier {
    return pointerInput(Unit) {
        detectTapGestures { offset ->
            coordinates?.let { rectF ->
                if (rectF.contains(offset.x, offset.y)) click()
            }
        }
    }
}
