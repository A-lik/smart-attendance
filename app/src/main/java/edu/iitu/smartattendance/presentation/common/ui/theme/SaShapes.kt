package edu.iitu.smartattendance.presentation.common.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val LocalSaShapes = staticCompositionLocalOf { SaShapes() }

data class SaShapes(
    val none: RoundedCornerShape = RoundedCornerShape(size = 0.dp),
    val radius8: RoundedCornerShape = RoundedCornerShape(size = 8.dp),
    val radius12: RoundedCornerShape = RoundedCornerShape(size = 12.dp),
    val radius16: RoundedCornerShape = RoundedCornerShape(size = 16.dp),
    val radius20: RoundedCornerShape = RoundedCornerShape(size = 20.dp),
    val bottomRadius20: RoundedCornerShape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
    val percent50: RoundedCornerShape = RoundedCornerShape(percent = 50)
)