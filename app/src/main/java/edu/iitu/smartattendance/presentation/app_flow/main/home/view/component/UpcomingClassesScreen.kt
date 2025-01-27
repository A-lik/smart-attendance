package edu.iitu.smartattendance.presentation.app_flow.main.home.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.main.home.model.HomeEvent
import edu.iitu.smartattendance.presentation.common.ui.component.HeightSpacer
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun UpcomingClassesScreen(
    dispatch: (HomeEvent) -> Unit
) {
    val systemBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()

    HeaderSection(systemBarPadding)
    ClassesSection(dispatch, systemBarPadding)

}

@Composable
private fun HeaderSection(
    systemBarPadding: Dp
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = SaTheme.shapes.radius12,
        color = SaColor.Primary400,
        contentColor = SaColor.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    bottom = 40.dp,
                    top = 8.dp + systemBarPadding
                )
        ) {
            Text(
                text = stringResource(id = R.string.main_screen_title),
                style = SaTheme.typography.titleBold24
            )
        }
    }
}

@Composable
private fun ClassesSection(
    dispatch: (HomeEvent) -> Unit,
    systemBarPadding: Dp
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = systemBarPadding + SaPadding.large().calculateBottomPadding()
            ),
        contentPadding = PaddingValues(
            vertical = SaPadding.medium().calculateBottomPadding(),
            horizontal = SaPadding.small().calculateTopPadding()
        )
    ) {
        items(12) {
            ClassCard(
                title = "System Level Programming",
                mainIcon = R.drawable.bg_class_detail,
                locationIcon = R.drawable.ic_location,
                location = "423",
                time = "12:34",
                dispatch
            )
            HeightSpacer(value = SaPadding.small().calculateTopPadding())
        }
    }
}

@Preview
@Composable
private fun ClassesPreview() = UpcomingClassesScreen({})