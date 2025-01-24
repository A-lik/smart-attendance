package edu.iitu.smartattendance.presentation.common.ui.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import edu.iitu.smartattendance.presentation.common.ui.component.appbar.enums.SaBottomNavBarIcon
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor
import edu.iitu.smartattendance.presentation.common.ui.theme.SaPadding

@Composable
fun SaBottomBar(
    contentColor: Color = SaColor.LightGray,
) {
//    val systemUiController = rememberSystemUiController()
//    systemUiController.setSystemBarsColor(
//        color = SaColor.Primary400,
//        darkIcons = false
//    )

    val navigationBarsPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    var selectedElement by remember { mutableStateOf(SaBottomNavBarIcon.HOME) }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = SaColor.Primary400,
        contentColor = contentColor,
        tonalElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    bottom = navigationBarsPadding + SaPadding.mediumSmall().calculateBottomPadding(),
                    top = SaPadding.small().calculateTopPadding()
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SaBottomNavBarIcon.entries.forEach { element ->
                SaBottomBarElement(
                    element = element,
                    isSelected = selectedElement == element,
                    onSelect = { selectedElement = element }
                )
            }
        }
    }
}

//@Composable
//fun RenderSaBottomBar(state: AppConfig) = when (state) {
//    is AppConfig.AuthConfig -> Unit
//    is AppConfig.MainConfig -> SaBottomBar()
//}

@Composable
@Preview(showBackground = true)
fun BottomNavigationBarPreview() {
    SaBottomBar()
}