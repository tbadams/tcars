package com.tadams.tcars.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.tadams.tcars.ui.theme.TCARSTheme
import kotlin.math.abs

@Composable
fun ProgressBar(
    progress: () -> Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    trackColor: Color = ProgressIndicatorDefaults.linearTrackColor,
) {
    val strokeCap = StrokeCap.Butt
    val coercedProgress = { progress().coerceIn(0f, 1f) }
    Canvas(
        modifier
            .then(IncreaseSemanticsBounds)
            .semantics(mergeDescendants = true) {
                progressBarRangeInfo = ProgressBarRangeInfo(coercedProgress(), 0f..1f)
            }
            .size(LinearIndicatorWidth, LinearIndicatorHeight)
    ) {
        val strokeWidth = size.height
        drawLinearIndicatorTrack(coercedProgress(), 1f,trackColor, strokeWidth * .66f, strokeCap)
        drawLinearIndicator(0f, coercedProgress(), color, strokeWidth, strokeCap)
    }
}

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
    startGap: Dp = gapWidth,
) {
    val width = size.width
    val height = size.height
    // Start drawing from the vertical center of the stroke
    val yOffset = height / 2 + ((height - strokeWidth) / 2) // Bottom justify the stroke

    val isLtr = layoutDirection == LayoutDirection.Ltr
    val barStart = (if (isLtr) startFraction else 1f - endFraction) * width
    val barEnd = (if (isLtr) endFraction else 1f - startFraction) * width

    // if there isn't enough space to draw the stroke caps, fall back to StrokeCap.Butt
    if (strokeCap == StrokeCap.Butt || height > width) {
        // Progress line
        val adjustedBarStart = (barStart + startGap.roundToPx()).coerceIn(0f..barEnd)
        drawLine(color, Offset(adjustedBarStart, yOffset), Offset(barEnd, yOffset), strokeWidth)
    } else {
        // need to adjust barStart and barEnd for the stroke caps
        val strokeCapOffset = strokeWidth / 2
        val coerceRange = strokeCapOffset..(width - strokeCapOffset)
        val adjustedBarStart = barStart.coerceIn(coerceRange)
        val adjustedBarEnd = barEnd.coerceIn(coerceRange)

        if (abs(endFraction - startFraction) > 0) {
            // Progress line
            drawLine(
                color,
                Offset(adjustedBarStart, yOffset),
                Offset(adjustedBarEnd, yOffset),
                strokeWidth,
                strokeCap,
            )
        }
    }
}

private fun DrawScope.drawLinearIndicatorTrack(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float,
    strokeCap: StrokeCap,
) = drawLinearIndicator(startFraction, endFraction, color, strokeWidth, strokeCap)


private val gapWidth = 2.dp // Totally wrong but w/e
private val LinearIndicatorWidth = 240.dp
private val LinearIndicatorHeight = 16.dp
private val SemanticsBoundsPadding: Dp = (24.dp - LinearIndicatorHeight) / 2
private val IncreaseSemanticsBounds: Modifier = Modifier
    .layout { measurable, constraints ->
        val paddingPx = SemanticsBoundsPadding.roundToPx()
        // We need to add vertical padding to the semantics bounds in other to meet
        // screenreader green box minimum size, but we also want to
        // preserve a visual appearance and layout size below that minimum
        // in order to maintain backwards compatibility. This custom
        // layout effectively implements "negative padding".
        val newConstraint = constraints.offset(0, paddingPx * 2)
        val placeable = measurable.measure(newConstraint)

        // But when actually placing the placeable, create the layout without additional
        // space. Place the placeable where it would've been without any extra padding.
        val height = placeable.height - paddingPx * 2
        val width = placeable.width
        layout(width, height) {
            placeable.place(0, -paddingPx)
        }
    }
    .semantics(mergeDescendants = true) {}
    .padding(vertical = SemanticsBoundsPadding)

@Preview
@Composable
fun PreviewProgress() {
    TCARSTheme {
        ProgressBar({0.45f})
    }
}

@Preview
@Composable
fun PreviewBigProgress() {
    TCARSTheme {
        ProgressBar({0.88f}, Modifier.fillMaxWidth(), )
    }
}