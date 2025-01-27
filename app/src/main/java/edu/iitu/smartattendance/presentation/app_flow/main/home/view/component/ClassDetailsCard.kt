package edu.iitu.smartattendance.presentation.app_flow.main.home.view.component

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.iitu.smartattendance.R
import edu.iitu.smartattendance.presentation.common.ui.component.HeightSpacer
import edu.iitu.smartattendance.presentation.common.ui.component.WidthSpacer
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme

@Composable
fun ClassDetailsCard(
    iconResId: Int,
//    spacerPadding: Dp
) {
    HeightSpacer()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentWidth()
    ) {
        Icon(imageVector = ImageVector.vectorResource(id = iconResId), contentDescription = null, tint = Color.Unspecified)
        WidthSpacer(value = SaPadding.small().calculateBottomPadding())
        Column {
            Text(text = "Text Text", style = SaTheme.typography.subheadRegular)
            Text(text = "Text Text", style = SaTheme.typography.subheadRegular, color = SaColor.SubtleText)
        }
    }
}