package com.tadams.tcars.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.BIG_BUTTON_WIDTH
import com.tadams.tcars.ui.theme.Bluey
import com.tadams.tcars.ui.theme.CONTENT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.CONTENT_PADDING
import com.tadams.tcars.ui.theme.Green
import com.tadams.tcars.ui.theme.HORIZONTAL_BAR_HEIGHT
import com.tadams.tcars.ui.theme.HORIZONTAL_BAR_MIN_WIDTH
import com.tadams.tcars.ui.theme.INTER_FRAME_GAP
import com.tadams.tcars.ui.theme.INTRA_FRAME_GAP
import com.tadams.tcars.ui.theme.Ice
import com.tadams.tcars.ui.theme.SURFACE_PADDING
import com.tadams.tcars.ui.theme.SWEPT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.Sunflower
import com.tadams.tcars.ui.theme.TCARSTheme
import com.tadams.tcars.ui.theme.TextViolet
import com.tadams.tcars.ui.theme.Tomato
import com.tadams.tcars.ui.theme.tcarsColorScheme

@Composable
fun Bar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp),
    color: Color = MaterialTheme.colorScheme.surface,
    alignment: Alignment = Alignment.BottomStart,
    content: @Composable () -> Unit = {}
) {
    Surface(
        modifier
            .defaultMinSize(HORIZONTAL_BAR_HEIGHT, HORIZONTAL_BAR_HEIGHT),
        shape,
        color
    ) {
        Box(
            Modifier
                .padding(SURFACE_PADDING),
            contentAlignment = alignment
        ) {
            content()
        }
    }
}

@Composable
fun BarColumn(
    isLeft: Boolean = true,
    modifier: Modifier = Modifier,
    spacing:Dp = INTRA_FRAME_GAP,
    topContent: @Composable (() -> Unit)? = {

    },
    bottomContent: @Composable (() -> Unit)? = {

    },
    width: Dp = BIG_BUTTON_WIDTH,
    content: @Composable (ColumnScope.() -> Unit)? = {
        Bar(
            Modifier
                .defaultMinSize(width, HORIZONTAL_BAR_MIN_WIDTH)
                .fillMaxWidth()
                .weight(1f),
        )
    }
) {
    Column(
        modifier.width(IntrinsicSize.Max),
        Arrangement.spacedBy(spacing)
    ) {
        topContent?.let {
            Bar(
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(SWEPT_CORNER_RADIUS, SWEPT_CORNER_RADIUS),
                shape = RoundedCornerShape(
                    topStart = if (isLeft) SWEPT_CORNER_RADIUS else 0.dp,
                    topEnd = if (!isLeft) SWEPT_CORNER_RADIUS else 0.dp
                ),
                alignment = if (isLeft) Alignment.BottomEnd else Alignment.BottomStart
            ) {
                it()
            }
        }
        content?.invoke(this)
        bottomContent?.let {
            Bar(
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(SWEPT_CORNER_RADIUS, SWEPT_CORNER_RADIUS),
                shape = RoundedCornerShape(
                    bottomStart = if (isLeft) SWEPT_CORNER_RADIUS else 0.dp,
                    bottomEnd = if (!isLeft) SWEPT_CORNER_RADIUS else 0.dp
                ),
                alignment = if (isLeft) Alignment.TopEnd else Alignment.TopStart
            ) {
                it()
            }
        }
    }
}

@Composable
fun BarRow(
    isTop: Boolean = true,
    modifier: Modifier = Modifier,
    spacing:Dp = INTRA_FRAME_GAP,
    startContent: @Composable (() -> Unit)? = {

    },
    endContent: @Composable (() -> Unit)? = {

    },
    height: Dp = HORIZONTAL_BAR_HEIGHT,
    content: @Composable (RowScope.() -> Unit)? = {
        Bar(
            Modifier
                .defaultMinSize(height, HORIZONTAL_BAR_MIN_WIDTH)
                .fillMaxHeight()
                .weight(1f),
        )
    }
) {
    Row(
        modifier.height(IntrinsicSize.Max),
        Arrangement.spacedBy(spacing)
    ) {
        startContent?.let {
            Bar(
                Modifier
                    .fillMaxHeight()
                    .defaultMinSize(SWEPT_CORNER_RADIUS, SWEPT_CORNER_RADIUS),
                shape = RoundedCornerShape(
                    topStart = if (isTop) SWEPT_CORNER_RADIUS else 0.dp,
                    bottomStart = if (!isTop) SWEPT_CORNER_RADIUS else 0.dp
                ),
                alignment = Alignment.BottomEnd
            ) {
                it()
            }
        }
        content?.invoke(this)
        endContent?.let {
            Bar(
                Modifier
                    .fillMaxHeight()
                    .defaultMinSize(SWEPT_CORNER_RADIUS, SWEPT_CORNER_RADIUS),
                shape = RoundedCornerShape(
                    topEnd = if (isTop) SWEPT_CORNER_RADIUS else 0.dp,
                    bottomEnd = if (!isTop) SWEPT_CORNER_RADIUS else 0.dp
                ),
                alignment = Alignment.BottomEnd
            ) {
                it()
            }
        }
    }
}

@Composable
fun BarFrame( // Row then columns
    modifier: Modifier = Modifier,
    startColumn: @Composable (ColumnScope.() -> Unit)? = {BarColumn(true)},
    endColumn: @Composable (ColumnScope.() -> Unit)? = null,
    topRow: @Composable (RowScope.() -> Unit)? = { Bar(Modifier.fillMaxWidth())},
    bottomRow: @Composable (RowScope.() -> Unit)? = { Bar(Modifier.fillMaxHeight().fillMaxWidth())},
    contentShape: Shape = RoundedCornerShape(
        if (startColumn != null && topRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (endColumn != null && topRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (endColumn != null && bottomRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (startColumn != null && bottomRow != null) CONTENT_CORNER_RADIUS else 0.dp
    ),
    content: @Composable () -> Unit
) {
    Row(
        modifier
            .height(IntrinsicSize.Max)
            .padding(vertical = INTER_FRAME_GAP / 2)
    ) {
        Column( // Start column
            Modifier.width(IntrinsicSize.Max),
            Arrangement.spacedBy(INTRA_FRAME_GAP)
        ) {
            startColumn?.invoke(this)
        }
        Column( // Content + top/bottom bars column
            Modifier.weight(1f)
        ) {
            Row(
                Modifier.height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(INTRA_FRAME_GAP)
            ) {
                topRow?.invoke(this)
            }
            Box( // Content
                Modifier.weight(1f).background(MaterialTheme.colorScheme.surface)
            ) {
                    Box(
                        Modifier
                            .background(MaterialTheme.colorScheme.background, contentShape)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(CONTENT_PADDING)
                    ) {
                        CompositionLocalProvider(
                            LocalContentColor provides MaterialTheme.colorScheme.onBackground
                        ) {
                            content()
                        }
                    }
            }
            Row(
                Modifier.height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(INTRA_FRAME_GAP),
                verticalAlignment = Alignment.Bottom
            ) {
                bottomRow?.invoke(this)
            }
        }
        Column( // End column
            Modifier.width(IntrinsicSize.Max),
            Arrangement.spacedBy(INTRA_FRAME_GAP)
        ) {
            endColumn?.invoke(this)
        }
    }
}

@Composable
fun WideBarFrame( // Column then rows
    modifier: Modifier = Modifier,
    startColumn: @Composable (ColumnScope.() -> Unit)? = { Bar(Modifier.fillMaxHeight())},
    endColumn: @Composable (ColumnScope.() -> Unit)? = { Bar(Modifier.fillMaxHeight())},
    topRow: @Composable (RowScope.() -> Unit)? = { BarRow(true)},
    bottomRow: @Composable (RowScope.() -> Unit)? = null,
    contentShape: Shape = RoundedCornerShape(
        if (startColumn != null && topRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (endColumn != null && topRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (endColumn != null && bottomRow != null) CONTENT_CORNER_RADIUS else 0.dp,
        if (startColumn != null && bottomRow != null) CONTENT_CORNER_RADIUS else 0.dp
    ),
    content: @Composable () -> Unit
) {
    Column(
        modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
            .padding(INTER_FRAME_GAP / 2)
    ) {
        Row(  // Top Row
            Modifier.height(IntrinsicSize.Max),
            Arrangement.spacedBy(INTRA_FRAME_GAP)
        ) {
            topRow?.invoke(this)
        }
        Row( // Content + Start/End columns
            Modifier.weight(1f)
        ) {
            Column(
                Modifier.width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.spacedBy(INTRA_FRAME_GAP)
            ) {
                startColumn?.invoke(this)
            }
            Box(
                Modifier.weight(1f).background(MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.background, contentShape)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(CONTENT_PADDING)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides MaterialTheme.colorScheme.onBackground
                    ) {
                        content()
                    }
                }
            }
            Column (
                Modifier.width(IntrinsicSize.Max),
                verticalArrangement = Arrangement.spacedBy(INTRA_FRAME_GAP)
            ) {
                endColumn?.invoke(this)
            }
        }
        Row( // End Row
            Modifier.height(IntrinsicSize.Max),
            Arrangement.spacedBy(INTRA_FRAME_GAP)
        ) {
            bottomRow?.invoke(this)
        }
    }
}


@Preview
@Composable
private fun PreviewSwept() {
    TCARSTheme(tcarsColorScheme(Tomato, onBackground = TextViolet)) {
        BarFrame(
            startColumn = {
                BarColumn(true) {
                    Bar(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                    Bar(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                    Bar(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    )
                }
            },
            endColumn = null,
            topRow = {
                Bar(Modifier.weight(1f))
                Bar(Modifier.weight(1f))
                Bar(Modifier.weight(1f))
            },
            bottomRow = {
                Bar(Modifier.weight(1f))
                Bar(Modifier.weight(1f))
                Spacer(Modifier.weight(1f))
            },
        ) {
            Text(
                "Unidentified vessel travelling at sub warp speed, bearing 235.7." +
                " Fluctuations in energy readings from it, Captain. All transporters off. " +
                "A strange set-up, but I'd say the graviton generator is depolarized. " +
                "The dark colourings of the scrapes are the leavings of natural rubber," +
                " a type of non-conductive sole used by researchers experimenting with electricity." +
                " The molecules must have been partly de-phased by the anyon beam."
            )
        }
    }
}

@Preview
@Composable
private fun PreviewRightFrame() {
    TCARSTheme {
        BarFrame(
            startColumn = null,
            endColumn = {
                BarColumn(false)
            },
        ) {
            Text(
                "Cmdr Riker's nervous system has been invaded by an unknown microorganism. " +
                    "The organisms fuse to the nerve, intertwining at the molecular level. " +
                    "That's why the transporter's biofilters couldn't extract it.",
                Modifier.padding(bottom = 50.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTopRightFrame() {
    TCARSTheme(tcarsColorScheme(Bluey, onBackground = Sunflower)) {
        BarFrame(
            startColumn = null,
            endColumn = {
                BarColumn(false, bottomContent = null)
            },
            bottomRow = null
        ) {
            Text(
                "You saw something as tasty as meat, but inorganically materialized out " +
                    "of patterns used by our transporters."
            )
        }
    }
}

@Preview
@Composable
private fun PreviewTopWideFrame() {
    TCARSTheme(tcarsColorScheme(Green, onBackground = Ice)) {
        WideBarFrame(
            topRow = {BarRow(true)}
        ) {
            Text(
                "You saw something as tasty as meat, but inorganically materialized out " +
                    "of patterns used by our transporters."
            )
        }
    }
}