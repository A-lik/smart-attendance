package edu.iitu.smartattendance.presentation.common.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import edu.iitu.smartattendance.presentation.app_flow.auth.view.components.authScreenGradient
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

@Composable
fun Modifier.clickWithRipple(
    onClick: () -> Unit,
    enabled: Boolean = true,
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified
) = clickable(
    enabled = enabled,
    interactionSource = remember { MutableInteractionSource() },
    indication = rememberRipple(
        color = SaColor.White,
        bounded = bounded,
        radius = radius
    ),
    onClick = onClick,
)

//@Composable
//fun Modifier.saBackground(state: AppConfig) = when (state) {
////    AppConfig.AuthConfig -> background(authScreenGradient())
////    is AppConfig.MainConfig -> when (state) {
////        AppConfig.MainConfig.HomeConfig -> background(SaColor.Primary400)
////        AppConfig.MainConfig.DetailsScreen -> background(Color.Black)
////    }
//    AuthConfig -> background(authScreenGradient())
//    MainConfig.DetailsScreen -> background(Color.Black)
//    else -> background(SaColor.Primary400)
////    MainConfig.HomeScreen -> TODO()
////    MainConfig.ProfileScreen -> TODO()
//}

@Composable
fun HeightSpacer(value: Dp) = Spacer(modifier = Modifier.height(value))

@Composable
fun WidthSpacer(value: Dp) = Spacer(modifier = Modifier.width(value))
