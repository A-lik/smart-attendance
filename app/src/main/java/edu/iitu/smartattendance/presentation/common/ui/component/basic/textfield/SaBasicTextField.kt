package edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.containerColor
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.indicatorColor
import edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield.SaBasicTextFieldDefaults.textColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
private fun HelperText(
    helperText: (@Composable () -> Unit)?,
) {
    if (helperText == null) return
    helperText()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    helperText: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = SaTheme.typography.headlineRegular,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = SaTheme.shapes.radius12,
    colors: TextFieldColors = SaBasicTextFieldDefaults.colors(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SaBasicTextFieldDefaults.HelperSpacing)
    ) {
        val textColor = colors.textColor(enabled).value
        val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

        BasicTextField(
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
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(rememberUpdatedState(if (isError) colors.errorCursorColor else colors.cursorColor).value),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = value,
                    visualTransformation = visualTransformation,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
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