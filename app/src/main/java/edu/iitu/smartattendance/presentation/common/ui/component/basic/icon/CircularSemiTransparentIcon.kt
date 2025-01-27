package edu.iitu.smartattendance.presentation.common.ui.component.basic.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
internal fun CircularSemiTransparentIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    colors: IconButtonColors = SaIconDefault.Basic.colors()
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(colors.containerColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector,
            contentDescription = null,
            tint = colors.contentColor
        )
    }
}