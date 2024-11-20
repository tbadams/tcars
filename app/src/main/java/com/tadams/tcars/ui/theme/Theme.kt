package com.tadams.tcars.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

fun tcarsColorScheme(
    primary: Color = Gold,
    onPrimary: Color = Black,
    secondary: Color = Bluey,
    onSecondary: Color = Black,
    tertiary: Color = Tomato,
    onTertiary: Color = Black,
    surface: Color = primary,
    onSurface: Color = Black,
    background: Color = Black,
    onBackground: Color = SpaceWhite
) = darkColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    tertiary = tertiary,
    onTertiary = onTertiary,
    surface = surface,
    onSurface = onSurface,
    background = background,
    onBackground = onBackground
)

private val DarkColorScheme = tcarsColorScheme()


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

@Composable
fun TCARSTheme(
    colorScheme: ColorScheme = DarkColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}