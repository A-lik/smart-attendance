package edu.iitu.smartattendance.presentation.common.ui.component.basic.icon

import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

sealed interface SaIconDefault {
    data object Basic : SaIconDefault {
        @Composable
        fun colors() = IconButtonDefaults.iconButtonColors(
            containerColor = SaColor.Gray100,
            contentColor = SaColor.Primary300,
            disabledContainerColor = SaColor.Gray200,
            disabledContentColor = SaColor.Gray700
        )
    }

//    data object Alternative : FidIconDefault {
//        @Composable
//        fun colors() = IconButtonDefaults.iconButtonColors(
//            containerColor = FidTheme.colors.backgrounds.quarterary,
//            contentColor = FidTheme.colors.foregrounds.secondary,
//            disabledContainerColor = FidTheme.colors.backgrounds.tertiary,
//            disabledContentColor = FidTheme.colors.foregrounds.secondary
//        )
//    }
}