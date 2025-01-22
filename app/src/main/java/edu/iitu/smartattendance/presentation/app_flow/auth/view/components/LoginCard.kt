package edu.iitu.smartattendance.presentation.app_flow.auth.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthEvent
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun LoginCard(
    state: AuthState.EmailAuth,
    dispatch: (AuthEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = SaPadding
                    .medium()
                    .calculateTopPadding(),
            )
            .wrapContentSize()
            .background(
                color = SaColor.White,
                shape = SaTheme.shapes.radius16
            )
    ) {
       LoginForm(state = state, dispatch = dispatch)
    }
}

@Composable
@Preview
fun LoginCardPreview() = LoginCard(state = AuthState.EmailAuth(), dispatch = {})