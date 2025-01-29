package edu.iitu.smartattendance.presentation.common.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {

    @Serializable
    data object Auth : AppDestination

    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Notifications : AppDestination

    @Serializable
    data object Profile : AppDestination

    @Serializable
    data object IdkYet : AppDestination
}