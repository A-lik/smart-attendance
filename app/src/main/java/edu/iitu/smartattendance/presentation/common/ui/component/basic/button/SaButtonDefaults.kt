package edu.iitu.smartattendance.presentation.common.ui.component.basic.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

object SaButtonDefaults {
    object ContentPadding {
        private fun paddingValues(
            vertical: Dp,
            horizontal: Dp = 16.dp,
        ) = PaddingValues(
            vertical = vertical,
            horizontal = horizontal,
        )

        fun extraSmall() = paddingValues(vertical = 10.dp)
        fun small() = paddingValues(vertical = 12.dp)
        fun medium() = paddingValues(vertical = 16.dp)
        fun large() = paddingValues(vertical = 20.dp)
        fun extraLarge() = paddingValues(vertical = 24.dp)
    }

    sealed interface SaButtonContentType {
        @Composable
        fun colors(): ButtonColors

        data object Basic : SaButtonContentType {
            @Composable
            override fun colors() = ButtonDefaults.buttonColors(
                containerColor = SaColor.Primary400,
                contentColor = SaColor.White,
                disabledContainerColor = SaColor.Primary200,
                disabledContentColor = SaColor.SubtleText,
            )
        }
    }
}