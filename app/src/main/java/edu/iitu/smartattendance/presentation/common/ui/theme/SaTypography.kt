package edu.iitu.smartattendance.presentation.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import edu.iitu.smartattendance.R

val LocalSaTypography = staticCompositionLocalOf { SaTypography() }

private val regularFont = FontFamily(
    Font(
        resId = R.font.inter_regular,
        weight = FontWeight.Normal
    )
)

private val mediumFont = FontFamily(
    Font(
        resId = R.font.inter_medium,
        weight = FontWeight.Medium
    )
)

private val lightFont = FontFamily(
    Font(
        resId = R.font.inter_light,
        weight = FontWeight.Light
    )
)

private val semiBoldFont = FontFamily(
    Font(
        resId = R.font.inter_semibold,
        weight = FontWeight.SemiBold
    )
)

private val boldFont = FontFamily(
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold
    )
)

data class SaTypography(
    val titleBold32: TextStyle = TextStyle(
        fontFamily = boldFont,
        fontSize = 32.sp,
        lineHeight = 45.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val titleBold24: TextStyle = TextStyle(
        fontFamily = boldFont,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val titleBold16: TextStyle = TextStyle(
        fontFamily = boldFont,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val titleBold: TextStyle = TextStyle(
        fontFamily = boldFont,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val subheadRegular: TextStyle = TextStyle(
        fontFamily = regularFont,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val titleMedium: TextStyle = TextStyle(
        fontFamily = mediumFont,
        fontSize = 30.sp
    ),
    val titleRegular20: TextStyle = TextStyle(
        fontFamily = regularFont,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val titleRegular: TextStyle = TextStyle(
        fontFamily = regularFont,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
    val headlineRegular: TextStyle = TextStyle(
        fontFamily = regularFont,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        lineHeightStyle = LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None
        )
    ),
)

//// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//    /* Other default text styles to override
//    titleLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
//)