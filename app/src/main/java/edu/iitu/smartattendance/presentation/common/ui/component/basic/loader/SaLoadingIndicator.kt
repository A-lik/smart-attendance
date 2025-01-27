package edu.iitu.smartattendance.presentation.common.ui.component.basic.loader

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import edu.iitu.smartattendance.presentation.common.ui.theme.SaColor

@Composable
fun SaLoadingIndicator(isFullScreen: Boolean = true) {
    BackHandler {}
    Box(
        Modifier.then(
            if (isFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
        ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = SaColor.White
        )
    }
}