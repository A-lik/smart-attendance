package edu.iitu.smartattendance.presentation.common.ui.component.basic.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

object SaBasicTextFieldDefaults {

    val HelperSpacing = 4.dp
    val BorderSize = 1.dp
    val TextFieldHorizontalPadding = 16.dp
    val TextFieldVerticalPadding = 6.dp

    @Composable
    fun colors(
        textColor: Color = SaColor.Black,
        disabledTextColor: Color = SaColor.LightGray,
//        errorTextColor: Color = SaTheme.colors.textColors.negative,
        containerColor: Color = SaColor.White,
//        iconColor: Color = SaTheme.colors.foregrounds.quarterary,
        labelColor: Color = SaColor.LightGray,
        unfocusedLabelColor: Color = SaColor.LightGray,
        placeholderColor: Color = SaColor.SubtleText,
        focusedIndicatorColor: Color = SaColor.Black,
        unfocusedIndicatorColor: Color = SaColor.Gray300,
//        errorColor: Color = SaTheme.colors.borderColors.negative,
    ) = TextFieldDefaults.colors(
        focusedTextColor = textColor,
        unfocusedTextColor = textColor,
        disabledTextColor = disabledTextColor,
//        errorTextColor = errorTextColor,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        disabledContainerColor = containerColor,
        errorContainerColor = containerColor,
        cursorColor = focusedIndicatorColor,
//        errorCursorColor = errorColor,
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        disabledIndicatorColor = SaColor.Transparent,
//        errorIndicatorColor = errorColor,
//        focusedLeadingIconColor = iconColor,
//        unfocusedLeadingIconColor = iconColor,
        disabledLeadingIconColor = disabledTextColor,
//        errorLeadingIconColor = iconColor,
//        focusedTrailingIconColor = iconColor,
//        unfocusedTrailingIconColor = iconColor,
//        disabledTrailingIconColor = iconColor,
//        errorTrailingIconColor = iconColor,
        focusedLabelColor = labelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        disabledLabelColor = disabledTextColor,
        errorLabelColor = labelColor,
        focusedPlaceholderColor = placeholderColor,
        unfocusedPlaceholderColor = placeholderColor,
        errorPlaceholderColor = placeholderColor
    )

    @Composable
    fun TextFieldColors.textColor(
        enabled: Boolean,
    ): State<Color> {
        val targetValue = when {
            !enabled -> disabledTextColor
            else -> unfocusedTextColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    fun TextFieldColors.indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            !enabled -> disabledIndicatorColor
            isError -> errorIndicatorColor
            focused -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = 150))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    fun TextFieldColors.containerColor(
        enabled: Boolean,
    ): State<Color> {

        val targetValue = when {
            !enabled -> disabledContainerColor
            else -> unfocusedContainerColor
        }
        return animateColorAsState(targetValue, tween(durationMillis = 150))
    }

    fun contentPaddingWithoutLabel(
        start: Dp = TextFieldHorizontalPadding,
        top: Dp = TextFieldHorizontalPadding,
        end: Dp = TextFieldHorizontalPadding,
        bottom: Dp = TextFieldHorizontalPadding
    ): PaddingValues = PaddingValues(start = start, top = top, end = end, bottom = bottom)

    fun contentPaddingWithLabel(
        start: Dp = TextFieldHorizontalPadding,
        end: Dp = TextFieldHorizontalPadding,
        top: Dp = TextFieldVerticalPadding,
    ): PaddingValues = PaddingValues(start = start, top = top, end = end)
}