package edu.iitu.smartattendance.presentation.common.ui.component.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

@Composable
fun SaScaffold(
    modifier : Modifier = Modifier,
) {}

data object SaScaffoldDefaults {
    @Composable
    fun colors(
        contentColor: Color = SaColor.White,
        containerColor: Color = SaColor.Primary400,
        topBarContentColor: Color = SaColor.Primary400
    ) = SaScaffoldColors(
        contentColor,
        containerColor,
        topBarContentColor
    )
}

data class SaScaffoldColors(
    val contentColor: Color,
    val containerColor: Color?,
    val topBarContentColor: Color
)