package edu.iitu.smartattendance.presentation.app_flow.main.home.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.common.ui.component.WidthSpacer
import edu.iitu.smartattendance.presentation.common.ui.component.clickWithRipple
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun CardComponent(
    title: String,
    mainIcon: Int,
    locationIcon: Int,
    location: String,
    time: String,
    dispatch: (HomeEvent) -> Unit
) {
    val callbacks = rememberCallbacks(dispatch)

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(SaTheme.shapes.radius8)
            .background(SaColor.White)
            .clickWithRipple(
                onClick = callbacks.viewClassDetailsClicked
            )
        
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(116.dp)
                .padding(
                    vertical = SaPadding
                        .small()
                        .calculateBottomPadding(),
                    horizontal = SaPadding
                        .small()
                        .calculateTopPadding()
                )
        ) {
            Icon(
                painterResource(id = mainIcon),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier
                    .clip(SaTheme.shapes.radius12)
            )

            WidthSpacer(value = SaPadding.small().calculateTopPadding())

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = title, style = SaTheme.typography.titleBold16, modifier = Modifier.wrapContentWidth())

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                vertical = SaPadding
                                    .extraSmall()
                                    .calculateBottomPadding()
                            )
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = locationIcon), contentDescription = ""
                        )

                        WidthSpacer(value = SaPadding.mediumSmall().calculateTopPadding())

                        Text(text = location, style = SaTheme.typography.headlineRegular)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = time, style = SaTheme.typography.headlineRegular)
                }
            }
        }
    }
}

private data class HomeCallbacks(
    val viewClassDetailsClicked: () -> Unit
)

@Composable
private fun rememberCallbacks(
    dispatch: (HomeEvent) -> Unit
) : HomeCallbacks = remember {
    HomeCallbacks(
        viewClassDetailsClicked = { dispatch(HomeEvent.ViewClassDetailsClicked) }
    )
}

fun Long.decTime() = (this - System.currentTimeMillis()) / 1000


@Preview
@Composable
private fun CardPreview() =
    CardComponent(
        title = "System Level Programing",
        mainIcon = R.drawable.ic_launcher_foreground,
        locationIcon = R.drawable.ic_location,
        location = "423",
        time = "12:34",
    ) {}
