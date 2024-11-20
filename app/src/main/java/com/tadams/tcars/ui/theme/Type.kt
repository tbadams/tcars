package com.tadams.tcars.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// TODO should be condensed helvetica
fun tcarsType(
    title: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = -(0.0).sp
    ),
    subheader: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = (0.0).sp
    ),
    body: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = -(0.0).sp
    ),
) = Typography(
    headlineLarge = title,
    headlineMedium = title,
    headlineSmall = title,
    bodyLarge = body,
    bodyMedium = body,
    bodySmall = body,
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

// Set of Material typography styles to start with

val Typography = tcarsType()