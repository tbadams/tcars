package com.tadams.tcars.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tadams.tcars.R

val antonio = FontFamily(
    Font(R.font.antonio),
)

// TODO should be condensed helvetica
fun tcarsType(
    title: TextStyle = TextStyle(
        fontFamily = antonio,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = -(0.0).sp
    ),
    subheader: TextStyle = TextStyle(
        fontFamily = antonio,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        letterSpacing = (0.0).sp
    ),
    body: TextStyle = TextStyle(
        fontFamily = antonio,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = -(0.0).sp
    ),
    bodySmall: TextStyle = TextStyle(
        fontFamily = antonio,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = -(0.0).sp
    ),
) = Typography(
    headlineLarge = title,
    headlineMedium = title,
    headlineSmall = title,
    bodyLarge = body,
    bodyMedium = body,
    bodySmall = bodySmall,
    titleLarge = subheader,
    titleMedium = subheader,
    titleSmall = subheader,
    labelLarge = subheader,
    labelMedium = body,
    labelSmall = body,
    displayLarge = body,
    displayMedium = body,
    displaySmall = body,
)

fun Typography.tcarsTitle() = this.headlineLarge
fun Typography.tcarsSubheader() = this.titleLarge
fun Typography.tcarsBody() = this.bodyMedium

// Set of Material typography styles to start with

val Typography = tcarsType()