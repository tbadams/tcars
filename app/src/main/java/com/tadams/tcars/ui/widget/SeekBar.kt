@file:OptIn(ExperimentalMaterial3Api::class)

package com.tadams.tcars.ui.widget

import androidx.annotation.IntRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.LinearIndicatorHeight
import com.tadams.tcars.ui.theme.LinearIndicatorUnselectedHeightFraction
import com.tadams.tcars.ui.theme.TCARSTheme

@Composable
fun SeekBar(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SeekBarDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @IntRange(from = 0) steps: Int = 0,
    thumb: @Composable (SliderState) -> Unit = {
        SeekBarDefaults.Thumb(
            interactionSource = interactionSource,
            colors = colors,
            enabled = enabled
        )
    },
    track: @Composable (SliderState) -> Unit = { sliderState ->
        SeekBarDefaults.Track(
            colors = colors,
            enabled = enabled,
            sliderState = sliderState
        )
    },
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f
) {
    Slider(
        value,
        onValueChange,
        modifier,
        enabled,
        onValueChangeFinished = onValueChangeFinished,
        track = track,
        steps = steps,
        thumb = thumb,
        valueRange = valueRange
    )
}

object SeekBarDefaults {

    private val THUMB_WIDTH = 10.dp
    private val THUMB_HEIGHT = 22.dp

    @Composable
    fun colors(): SliderColors = SliderDefaults.colors(
        thumbColor = MaterialTheme.colorScheme.secondary,
        activeTrackColor = MaterialTheme.colorScheme.secondary,
        activeTickColor = Transparent,
        inactiveTickColor = Transparent,
        disabledActiveTickColor = Transparent,
        disabledInactiveTickColor = Transparent
    )
    @Composable
    @ExperimentalMaterial3Api
    fun Track(
        sliderState: SliderState,
        modifier: Modifier = Modifier,
        colors: SliderColors = colors(),
        enabled: Boolean = true
    ) {
        // Adapted from Material 3
        val inactiveTrackColor = if (enabled) colors.inactiveTrackColor else colors.disabledInactiveTrackColor
        val activeTrackColor = if (enabled) colors.activeTrackColor else colors.disabledActiveTrackColor
        val inactiveTickColor = if (enabled) colors.inactiveTickColor else colors.disabledInactiveTickColor
        val activeTickColor = if (enabled) colors.activeTickColor else colors.disabledActiveTickColor
        Canvas(
            modifier
                .fillMaxWidth()
                .height(LinearIndicatorHeight) // 4dp
        ) {
            drawTrack(
                if (sliderState.steps == 0) floatArrayOf() else FloatArray(sliderState.steps + 2) { it.toFloat() / (sliderState.steps + 1) },
                0f,
                (if (sliderState.valueRange.endInclusive - sliderState.valueRange.start == 0f) 0f else (sliderState.value.coerceIn(sliderState.valueRange.start, sliderState.valueRange.endInclusive) - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)).coerceIn(0f, 1f),
                inactiveTrackColor,
                activeTrackColor,
                inactiveTickColor,
                activeTickColor
            )
        }
    }

    @Composable
    fun Thumb(
        interactionSource: MutableInteractionSource,
        modifier: Modifier = Modifier,
        colors: SliderColors = SliderDefaults.colors(),
        enabled: Boolean = true,
        thumbSize: DpSize = DpSize(THUMB_WIDTH, THUMB_HEIGHT)
    ) {
        // Adapted from Material 3
        // I think I broke ripple, honestly probably better that way
        val interactions = remember { mutableStateListOf<Interaction>() }
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> interactions.add(interaction)
                    is PressInteraction.Release -> interactions.remove(interaction.press)
                    is PressInteraction.Cancel -> interactions.remove(interaction.press)
                    is DragInteraction.Start -> interactions.add(interaction)
                    is DragInteraction.Stop -> interactions.remove(interaction.start)
                    is DragInteraction.Cancel -> interactions.remove(interaction.start)
                }
            }
        }

        val elevation = if (interactions.isNotEmpty()) {
            1.dp
        } else {
            1.dp
        }
        val shape = RoundedCornerShape(0.dp)

        @Suppress("DEPRECATION_ERROR")
        Spacer(
            modifier
                .size(thumbSize)
                .offset(10.dp, LinearIndicatorHeight - THUMB_HEIGHT + 2.75.dp) // no idea
                .indication(
                    interactionSource = interactionSource,
                    indication = androidx.compose.material.ripple.rememberRipple(
                        bounded = false,
                        radius = 40.dp / 2
                    )
                )
                .hoverable(interactionSource = interactionSource)
                .shadow(if (enabled) elevation else 0.dp, shape, clip = false)
                .background(if(enabled) colors.thumbColor else colors.disabledThumbColor, shape)
        )
    }

    private fun DrawScope.drawTrack(
        tickFractions: FloatArray,
        activeRangeStart: Float,
        activeRangeEnd: Float,
        inactiveTrackColor: Color,
        activeTrackColor: Color,
        inactiveTickColor: Color,
        activeTickColor: Color
    ) {
        val bottomY = LinearIndicatorHeight.roundToPx() - ((LinearIndicatorHeight.roundToPx() * LinearIndicatorUnselectedHeightFraction) / 2)
        val isRtl = layoutDirection == LayoutDirection.Rtl
        val sliderLeft = Offset(0f, bottomY)
        val sliderRight = Offset(size.width, bottomY)
        val sliderStart = if (isRtl) sliderRight else sliderLeft
        val sliderEnd = if (isRtl) sliderLeft else sliderRight
        val tickSize = 2.dp.toPx()
        val trackStrokeWidth = LinearIndicatorHeight.toPx()
        val sliderValueEnd = Offset(
            sliderStart.x +
                (sliderEnd.x - sliderStart.x) * activeRangeEnd,
            center.y
        )
        val shit = if(sliderValueEnd.x > 28) 28 else 0
        val sliderValueEndDisplay = Offset(
            sliderStart.x +
                (sliderEnd.x - sliderStart.x) * activeRangeEnd -shit ,
            center.y
        )

        val sliderValueStart = Offset(
            sliderStart.x +
                (sliderEnd.x - sliderStart.x) * activeRangeStart,
            center.y
        )

        val trackStart = Offset(
            if (sliderEnd.x - sliderValueEnd.x >= 50) sliderValueEnd.x + 50 else sliderEnd.x
            , // sorry
            sliderStart.y
        )

        drawLine( // Track
            inactiveTrackColor,
            trackStart,
            sliderEnd,
            trackStrokeWidth * LinearIndicatorUnselectedHeightFraction,
            StrokeCap.Square
        )

        drawLine( // Fill
            activeTrackColor,
            sliderValueStart,
            sliderValueEnd,
            trackStrokeWidth,
            StrokeCap.Square
        )

        for (tick in tickFractions) {
            val outsideFraction = tick > activeRangeEnd || tick < activeRangeStart
            drawCircle(
                color = if (outsideFraction) inactiveTickColor else activeTickColor,
                center = Offset(lerp(sliderStart, sliderEnd, tick).x, center.y),
                radius = tickSize / 2f
            )
        }
    }
}

@Preview
@Composable
fun SeekBarPreview() {
    TCARSTheme {
        SeekBar(value = 0.5f, onValueChange = {}, enabled = true)
    }
}