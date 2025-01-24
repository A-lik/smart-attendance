package edu.iitu.smartattendance.presentation.common.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNav() {
    val navController = rememberNavController()
//    val currentBackStackEntry = navController.currentBackStackEntryAsState().value


    Scaffold(
        containerColor = Color.Transparent,
//            bottomBar = { RenderSaBottomBar(state) }
    ) { innerPadding ->
        AppNavHost(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            navController = navController
        )
    }
}

/*
* Did navigation
* should continue on making Home screen cause it is shit rn
*
* */