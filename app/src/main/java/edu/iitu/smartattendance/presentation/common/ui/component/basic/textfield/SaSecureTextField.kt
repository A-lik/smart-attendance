package edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.containerColor
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.indicatorColor
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.textColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme
import java.nio.CharBuffer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaSecureTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = SaBasicTextFieldDefaults.colors(),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    shape: Shape = SaTheme.shapes.radius12,
    textStyle: TextStyle = SaTheme.typography.headlineRegular,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    helperText: (@Composable () -> Unit)? = null,
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    placeholder: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null
) {
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    val textColor = colors.textColor(enabled).value
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SaBasicTextFieldDefaults.HelperSpacing)
    ) {
        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.containerColor(enabled || readOnly).value, shape)
                .border(
                    width = SaBasicTextFieldDefaults.BorderSize,
                    color = colors.indicatorColor(
                        enabled = enabled || readOnly,
                        isError = isError,
                        interactionSource = interactionSource
                    ).value,
                    shape = shape
                )
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = TextFieldDefaults.MinHeight
                ),
            state = state,
            textStyle = mergedTextStyle,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(rememberUpdatedState(if (isError) colors.errorCursorColor else colors.cursorColor).value),
            keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
            onKeyboardAction = onKeyboardAction ?: KeyboardActionHandler {
                focusManager.moveFocus(
                    FocusDirection.Down
                )
            },
            textObfuscationMode = if (isPasswordVisible) TextObfuscationMode.Visible else TextObfuscationMode.Hidden,
            textObfuscationCharacter = '*',
            decorator = @Composable { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = String(CharArray(state.text.length) { '*' }),
                    innerTextField = innerTextField,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            PasswordIcon(isPasswordVisible = isPasswordVisible)
                        }
                    },
                    leadingIcon = leadingIcon,
                    placeholder = placeholder,
                    label = label,
                    enabled = true,
                    singleLine = true,
                    interactionSource = interactionSource,
                    visualTransformation = VisualTransformation.None,
                    contentPadding = if (label == null) {
                        SaBasicTextFieldDefaults.contentPaddingWithoutLabel()
                    } else {
                        SaBasicTextFieldDefaults.contentPaddingWithLabel()
                    },
                    container = {},
                    colors = colors
                )
            }
        )
        HelperText(helperText = helperText)

    }
}

@Composable
private fun HelperText(
    helperText: (@Composable () -> Unit)?,
) {
    if (helperText == null) return
    helperText()
}

@Composable
fun PasswordIcon(isPasswordVisible: Boolean) {
    Icon(
        ImageVector.vectorResource(
            id = when (isPasswordVisible) {
                false -> R.drawable.ic_hide_eye
                true -> R.drawable.ic_eye
            }
        ),
        modifier = Modifier.size(24.dp),
        contentDescription = null,
        tint = SaColor.LightGray
    )
}

@SuppressLint("ComposableNaming")
@Composable
fun setInitialValue(state: TextFieldState, value: CharArray) {
    LaunchedEffect(Unit) {
        if (value.isNotEmpty()) {
            state.setInitialValue(value)
        }
    }
}

fun TextFieldState.setInitialValue(value: CharArray) {
    val buffer = CharBuffer.wrap(value)
    edit {
        append(buffer)
    }
    buffer.clear()
}