package edu.iitu.smartattendance.presentation.common.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Auth : AppDestination

    @Serializable
    data object Home : AppDestination

}