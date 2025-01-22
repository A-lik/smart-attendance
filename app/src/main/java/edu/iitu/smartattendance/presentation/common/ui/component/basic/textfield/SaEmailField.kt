package edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.domain.auth.model.Email
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding

@Composable
fun SaEmailField(
    email: Email? = null,
    onEmailChange: (String) -> Unit,
    label: String = stringResource(R.string.label_email)
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SaPadding.small().calculateTopPadding())
    ) {
        val emailOrEmpty = email?.value ?: ""
        var emailText by remember { mutableStateOf(emailOrEmpty) }
        val emailIsInvalid = email == null && emailText.isNotBlank()
        val focusManger = LocalFocusManager.current

        SaBasicTextField(
            value = emailText,
            onValueChange = { input ->
                emailText = input
                onEmailChange(input)
            },
            isError = emailIsInvalid,
            helperText = {
                if (emailIsInvalid) {
                    Text(
                        text = stringResource(id = R.string.auth_email_helper),
                        color = SaColor.Error
                    )
                }
            },
            label = { Text(label) },
            keyboardActions = KeyboardActions(
                onNext = { focusManger.moveFocus(FocusDirection.Down)}
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )
    }
}