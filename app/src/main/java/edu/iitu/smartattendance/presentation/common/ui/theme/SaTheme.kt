package edu.iitu.smartattendance.presentation.common.ui.theme

import android.app.Activity
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    SideEffect {
        val window = (context as? Activity)?.window ?: return@SideEffect
        val insetsController = WindowInsetsControllerCompat(window, view)
//        insetsController.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE

//        insetsController.apply {
//            isAppearanceLightStatusBars = true
//            isAppearanceLightNavigationBars = true
//        }
    }


    val SaScheme =
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
    
    CompositionLocalProvider(
        LocalSaTypography provides SaTypography(),
        LocalSaShapes provides SaShapes()
    ) {
        ProvideTextStyle(value = SaTheme.typography.subheadRegular) {
            content()
        }
    }

}

object SaTheme{
    val typography: SaTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalSaTypography.current

    val shapes: SaShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalSaShapes.current
}