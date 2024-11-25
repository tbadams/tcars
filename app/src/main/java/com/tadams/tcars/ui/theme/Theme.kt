package com.tadams.tcars.ui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.tadams.tcars.ui.theme.Gold

private val DarkColorScheme = tcarsColorScheme()
private val SecondaryColorScheme = tcarsColorScheme(
    primary = Bluey,
    primaryContainer = Ice,
    secondary = Sunflower,
    secondaryContainer = Yellow,
    onBackground = Sunflower
)
private val GreenColors = tcarsColorScheme(
    primary = Green,
    primaryContainer = Ice,
    secondary = Ice,
)
private val RedAlertColors = tcarsColorScheme(
    primary = Mars,
    primaryContainer = Tomato,
    secondary = SpaceWhite,
)
private val TertiaryColorScheme = tcarsColorScheme(
    primary = Red,
    primaryContainer = Tomato,
    secondary = AfricanViolet,
    onBackground = TextViolet
)

@Composable
fun TCARSTheme(
    colorScheme: ColorScheme = DarkColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
    ) {
        CompositionLocalProvider(
            androidx.compose.material.ripple.LocalRippleTheme provides TCARSRipple,
            LocalContentColor provides colorScheme.onBackground
        ) {
            content()
        }
    }
}

private object TCARSRipple: RippleTheme {
    @Composable
    override fun defaultColor(): Color = MaterialTheme.colorScheme.onBackground

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        .12f,
        .12f,
        .16f,
        .12f
    )
}