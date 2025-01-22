package edu.iitu.smartattendance

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import edu.iitu.smartattendance.presentation.app_flow.AppState
import edu.iitu.smartattendance.presentation.app_flow.AppViewModel
import edu.iitu.smartattendance.presentation.common.app_navigation.AppNavigation
import edu.iitu.smartattendance.presentation.common.ui.theme.SaTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    private lateinit var appVm: AppViewModel
//    private lateinit var authVm: AuthViewModel
//    private lateinit var authExecutor: AuthExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        appVm = AppViewModel()
        setContent {
            SaTheme {
                AppNavigation(appVm)
            }
        }
    }
}