package edu.iitu.smartattendance.presentation.common.ui.component.appbar

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.presentation.app_flow.NavEventBus
import edu.iitu.smartattendance.presentation.common.navigation.AppDestination
import edu.iitu.smartattendance.presentation.common.ui.component.appbar.enums.SaBottomNavBarElement
import edu.iitu.smartattendance.presentation.common.ui.component.clickWithRipple
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SaBottomBarElement(
    isSelected: Boolean,
    element: SaBottomNavBarElement,
    onSelect: () -> Unit,
    contentColor: Color = SaColor.White,
    unselectedItemColor: Color = SaColor.LightGray,
) {

    val callbacks = rememberCallbacks()
    val navEvent = decideNavCallback(element, callbacks)

    val offset by animateDpAsState(if (isSelected) (-8).dp else 0.dp, label = "")
    val scale by animateFloatAsState(if (isSelected) 1.2f else 1f, label = "")

    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .offset(y = offset)
                .scale(scale),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = element.iconResId),
                contentDescription = null,
                tint = if (isSelected) contentColor else unselectedItemColor,
                modifier = Modifier
                    .clip(SaTheme.shapes.percent50)
                    .clickWithRipple(onClick = {
                        navEvent()
                        onSelect()
                    })
            )
        }
    }
}

private data class SaBottomBarCallbacks(
    val navigateToHome: () -> Unit,
    val navigateToNotifications: () -> Unit,
    val navigateToIdkYet: () -> Unit,
    val navigateToProfile: () -> Unit
)

@Composable
private fun rememberCallbacks(): SaBottomBarCallbacks = remember {
    SaBottomBarCallbacks(
        navigateToHome = { NavEventBus.emitNavEvent(AppDestination.Home) },
        navigateToNotifications = { NavEventBus.emitNavEvent(AppDestination.Notifications) },
        navigateToProfile = { NavEventBus.emitNavEvent(AppDestination.Profile) },
        navigateToIdkYet = { NavEventBus.emitNavEvent(AppDestination.IdkYet) }
    )
}

private fun decideNavCallback(element: SaBottomNavBarElement, callbacks: SaBottomBarCallbacks) = when (element) {
    SaBottomNavBarElement.HOME -> callbacks.navigateToHome
    SaBottomNavBarElement.NOTIFICATION -> callbacks.navigateToNotifications
    SaBottomNavBarElement.MAP -> callbacks.navigateToIdkYet
    SaBottomNavBarElement.PROFILE -> callbacks.navigateToProfile
}