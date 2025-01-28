package edu.iitu.smartattendance.presentation.app_flow.main.home.view.component

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.common.ui.component.HeightSpacer
import edu.iitu.smartattendance.presentation.common.ui.component.basic.button.SaBaseButton
import edu.iitu.smartattendance.presentation.common.ui.component.basic.icon.CircularSemiTransparentIcon
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun ClassDetailsScreen(
    dispatch: (HomeEvent) -> Unit
) {
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
    val context = LocalContext.current as FragmentActivity
    val callbacks = rememberCallbacks(context, dispatch)

    BackHandler {
        callbacks.navigateBackClicked()
    }

//    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.bg_class_detail), contentDescription = "")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = systemBarPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = SaPadding
                        .medium()
                        .calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 200.dp)
                    .fillMaxWidth(),
                text = "System Level Programming",
                style = SaTheme.typography.titleBold24
            )
            ClassDetailsCard(iconResId = R.drawable.ic_calendar)
            ClassDetailsCard(iconResId = R.drawable.ic_location_detail)
            ClassDetailsCard(iconResId = R.drawable.ic_location_detail)
            HeightSpacer(value = SaPadding.large().calculateBottomPadding())
            Text(text = stringResource(id = R.string.notes), style = SaTheme.typography.titleMedium)
            HeightSpacer()
            // TODO(Apply conditional rendering if text is present,
            //  this is for additional notes by teacher)
            Text(text = "")
            HeightSpacer()
            SaBaseButton(
                icon = { CircularSemiTransparentIcon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_qr)) },
                onClick = callbacks.checkInClicked,
                modifier = Modifier
                    .padding(horizontal = SaPadding.large().calculateTopPadding())
            ) {
                Text(
                    text = stringResource(id = R.string.check_in),
                    style = SaTheme.typography.titleMedium
                )
            }
        }
    }
}

private data class ClassDetailsCallbacks(
    val checkInClicked: () -> Unit,
    val navigateBackClicked: () -> Unit
)

@Composable
private fun rememberCallbacks(context: Context, dispatch: (HomeEvent) -> Unit) : ClassDetailsCallbacks =
    remember {
        ClassDetailsCallbacks(
            checkInClicked = { dispatch(HomeEvent.DetailsEvent.CheckInClicked(context)) },
            navigateBackClicked = { dispatch(HomeEvent.DetailsEvent.NavigateBackClicked) }
        )
    }

@Composable
@Preview(showBackground = true)
private fun ClassDetailsScreenPreview() = ClassDetailsScreen() {}