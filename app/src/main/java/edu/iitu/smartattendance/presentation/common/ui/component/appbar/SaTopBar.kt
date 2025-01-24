package edu.iitu.smartattendance.presentation.common.ui.component.appbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeState
import edu.iitu.smartattendance.presentation.common.mvi.StateMachine
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
internal fun SaTopBar(
    modifier: Modifier = Modifier,
    navIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    title: String? = null,
    containerColor: Color = Color.Transparent,
    contentColor: Color = SaColor.White,
    shape: RoundedCornerShape = SaTheme.shapes.none
) {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Surface(
        modifier = modifier.padding(top = statusBarPadding),
        color = containerColor,
        contentColor = contentColor,
        shape = shape
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (navIcon != null) {
                Box(
                    modifier = Modifier
                        .padding(SaPadding.small())
                        .align(Alignment.CenterStart)
                ) {
                    navIcon()
                }
            }
            if (title != null) {
                Text(
                    text = title,
                    style = SaTheme.typography.titleBold,
                    modifier = Modifier
                        .padding(
                            SaPadding
                                .small()
                                .calculateTopPadding() + 1.dp
                        )
                        .align(Alignment.Center)
                )
            }
            if (trailingIcon != null) {
                Box(
                    modifier = Modifier
                        .padding(SaPadding.small())
                        .align(Alignment.CenterEnd)
                ) {
                    trailingIcon()
                }
            }
        }
    }
}

@Composable
fun RenderSaTopBar(state: StateMachine.State) = when (state) {
    is HomeState.LoadedDetails -> SaTopBar(
        navIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .clip(SaTheme.shapes.percent50)
//                    .clickWithRipple(onClick = { dispatch(navEvent) })
            )
        }
    )
    else -> Unit
}