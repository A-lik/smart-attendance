package edu.iitu.smartattendance.presentation.common.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SaPadding {
    private fun paddingValues(
        vertical: Dp,
        horizontal: Dp = 16.dp,
    ) = PaddingValues(
        vertical = vertical,
        horizontal = horizontal,
    )

    fun extraSmall() = paddingValues(vertical = 4.dp)
    fun mediumSmall() = paddingValues(vertical = 8.dp)
    fun small() = paddingValues(vertical = 16.dp)
    fun medium() = paddingValues(vertical = 24.dp)
    fun large() = paddingValues(vertical = 32.dp)
    fun superLarge() = paddingValues(vertical = 48.dp)
}