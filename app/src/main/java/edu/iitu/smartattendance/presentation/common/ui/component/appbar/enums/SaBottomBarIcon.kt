package edu.iitu.smartattendance.presentation.common.ui.component.appbar.enums

import androidx.annotation.DrawableRes
import edu.iitu.smartattendance.R

enum class SaBottomNavBarElement(@DrawableRes val iconResId: Int) {
    HOME(iconResId = R.drawable.ic_home),
    NOTIFICATION(iconResId = R.drawable.ic_notification),
    MAP(iconResId = R.drawable.ic_map),
    PROFILE(iconResId = R.drawable.ic_profile)
}