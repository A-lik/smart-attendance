package edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import edu.iitu.smartattendance.R

@Composable
internal fun SaPasswordField(
    password: CharArray?,
    label: String = stringResource(R.string.label_password),
    onChange: (CharSequence) -> Unit,
    isError: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
) {
    val focusManager = LocalFocusManager.current
    val state = rememberTextFieldState()
    setInitialValue(state, password ?: charArrayOf())
    LaunchedEffect(state.text) {
        if (shouldHandleInput(password, state.text)) {
            onChange(state.text)
        }
    }

    SaSecureTextField(
        state = state,
        isError = isError,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction ?: KeyboardActionHandler { focusManager.clearFocus() },
        label = { Text(label) },
    )
}

private fun shouldHandleInput(password: CharArray?, text: CharSequence) =
    password == null && text.isNotEmpty() || text.isNotEmpty()