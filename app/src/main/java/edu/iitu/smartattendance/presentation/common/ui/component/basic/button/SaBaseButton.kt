package edu.iitu.smartattendance.presentation.common.ui.component.basic.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun SaBaseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: ButtonColors = SaButtonDefaults.SaButtonContentType.Basic.colors(),
    contentPadding: PaddingValues = SaButtonDefaults.ContentPadding.medium(),
    shape: Shape = SaTheme.shapes.radius20,
    border: BorderStroke? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val hasRipple = if (enabled) rememberRipple(color = contentColor) else null

    Surface(
        modifier = modifier
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = hasRipple,
                onClick = onClick,
                enabled = enabled
            ),
        color = containerColor,
        contentColor = contentColor,
        border = border
    ) {
        Box(
            modifier = Modifier
                .padding(contentPadding),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = content
            )
        }
    }
}


@Preview
@Composable
fun SaButtonPreview() = SaBaseButton(onClick = { /*TODO*/ }) {
    Text(
        text = "Button label",
        style = SaTheme.typography.titleRegular
    )
}