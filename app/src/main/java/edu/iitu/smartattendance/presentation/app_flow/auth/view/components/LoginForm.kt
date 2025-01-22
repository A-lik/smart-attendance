package edu.iitu.smartattendance.presentation.app_flow.auth.view.components

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthEvent
import edu.iitu.smartattendance.presentation.app_flow.auth.model.AuthState
import edu.iitu.smartattendance.presentation.app_flow.auth.model.isValid
import edu.iitu.smartattendance.presentation.common.ui.component.basic.button.SaBaseButton
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaEmailField
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaPasswordField
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun LoginForm(
    state: AuthState.EmailAuth,
    dispatch: (AuthEvent) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val callbacks = rememberCallbacks(dispatch)

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(SaPadding.medium())
    ) {

        LoginTextSection()

        Spacer(modifier = Modifier.height(SaPadding.large().calculateTopPadding()))

        SaEmailField(
            email = state.email,
            onEmailChange = callbacks.onEmailChanged
        )

        Spacer(modifier = Modifier.padding(SaPadding.extraSmall()))

        SaPasswordField(
            password = state.password,
            onChange = callbacks.onPasswordChanged,
            isError = false,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onKeyboardAction = { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(100.dp))

        SaBaseButton(
            onClick = callbacks.loginClick,
            enabled = state.isValid(),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = SaTheme.typography.headlineRegular
            )
        }
    }
}

private data class EmailAuthCallbacks(
    val onEmailChanged: (String) -> Unit,
    val onPasswordChanged: (CharSequence) -> Unit,
    val loginClick: () -> Unit
)

@Composable
private fun rememberCallbacks(
    dispatch: (AuthEvent) -> Unit
) : EmailAuthCallbacks = remember {
    EmailAuthCallbacks(
        onEmailChanged = { email -> dispatch(AuthEvent.EmailedChanged(email)) },
        onPasswordChanged = { password -> dispatch(AuthEvent.PasswordChanged(password.toCharArray())) },
        loginClick = { dispatch(AuthEvent.LoginClicked) }
    )
}

fun CharSequence.toCharArray() = CharArray(length) { get(it) }


@Composable
fun LoginTextSection() {
    Text(
        text = stringResource(id = R.string.login_form_title),
        style = SaTheme.typography.titleRegular,
        color = SaColor.Black
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = stringResource(id = R.string.login_form_subtitle),
        style = SaTheme.typography.subheadRegular,
        color = SaColor.SubtleText
    )
}