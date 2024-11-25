package com.tadams.tcars.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// https://www.thelcars.com/colors.php

val SpaceWhite = Color(0xFFF5F6FA)
val Gray = Color(0xFF666688)
val Black = Color(0xFF000000)

val Mars = Color(0xFFff2200)
val Red = Color(0xFFdd4444)
val Tomato = Color(0xFFff5555)
val Orange = Color(0xFFff7700)
val Peach = Color(0xFFff8866)
val Butterscotch = Color(0xFFff9966)
val Almond = Color(0xFFffaa90)
val AlmondCreme = Color(0xFFffbbaa)
val Gold = Color(0xFFffaa00)
val Sunflower = Color(0xFFffcc66)
val Yellow = Color(0xFFffcc33)
val Green = Color(0xFF33cc99)
val Ice = Color(0xFF88ccff)
val Sky = Color(0xFFaaaaff)
val Bluey = Color(0xFF7788ff) // HSL 232.5,100,73.3
val Blue = Color(0xFF4455ff)
val Violet = Color(0xFF9944ff)
val Lilac = Color(0xFFcc33ff)
val Magenta = Color(0xFFcc4499)
val AfricanViolet = Color(0xFFcc88ff)
val TextViolet = Color(0xFFcc77ff)
val VioletCreme = Color(0xFFddbbff)

fun tcarsColorScheme(
    primary: Color = Gold,
    primaryContainer: Color = Sunflower,
    secondary: Color = Bluey,
    secondaryContainer: Color = Ice,
    tertiary: Color = Red,
    tertiaryContainer: Color = Tomato,
    surface: Color = primary,
    onSurface: Color = Black,
    background: Color = Black,
    onBackground: Color = SpaceWhite,
    onPrimary: Color = onSurface,
    onSecondary: Color = onSurface,
    onTertiary: Color = onSurface,
    error: Color = Mars,
    onError: Color = onSurface,
) = darkColorScheme(
    primary = primary,
    primaryContainer = primaryContainer,
    onPrimary = onPrimary,
    secondary = secondary,
    secondaryContainer = secondaryContainer,
    onSecondary = onSecondary,
    tertiary = tertiary,
    tertiaryContainer = tertiaryContainer,
    onTertiary = onTertiary,
    surface = surface,
    onSurface = onSurface,
    background = background,
    onBackground = onBackground,
    error = error,
    onError = onError
)