package com.tadams.tcars.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.Black
import com.tadams.tcars.ui.theme.CONTENT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.CONTENT_PADDING
import com.tadams.tcars.ui.theme.Gold
import com.tadams.tcars.ui.theme.HORIZONTAL_BAR_HEIGHT
import com.tadams.tcars.ui.theme.INTER_FRAME_GAP
import com.tadams.tcars.ui.theme.INTRA_FRAME_GAP
import com.tadams.tcars.ui.theme.SURFACE_PADDING
import com.tadams.tcars.ui.theme.SWEPT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.SpaceWhite
import com.tadams.tcars.ui.theme.TCARSTheme

@Composable
fun LeftTopElbow(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    radius: Dp = SWEPT_CORNER_RADIUS,
    content: @Composable () -> Unit = {}
) {
    Surface(
        modifier
            .defaultMinSize(radius, radius)
        ,
        RoundedCornerShape(
            topStart = radius,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        color
    ) {
        Box(
            Modifier
                .padding(
                    SURFACE_PADDING,
                    radius,
                    SURFACE_PADDING,
                    SURFACE_PADDING
                ),
            contentAlignment = Alignment.BottomEnd
        ) {
            content()
        }
    }
}

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
fun HorizontalBar(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0.dp),
    color: Color = MaterialTheme.colorScheme.surface,
    alignment: Alignment = Alignment.BottomStart,
    content: @Composable () -> Unit = {}
) {
    Bar(
        modifier,
        shape = shape,
        color = color,
        alignment = alignment,
    ) {
        content()
    }
}


@Preview
@Composable
fun PreviewSwept() {
    TCARSTheme {
        Row(
            Modifier.padding(top = INTER_FRAME_GAP / 2)
        ) {
            Column(
                Modifier.width(IntrinsicSize.Max)
            ) {
                LeftTopElbow(
                    Modifier, Gold
                ) {
                    Text("HELLO WORLD")
                }
                Spacer(Modifier.height(INTRA_FRAME_GAP))
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    color = Gold,
                ) {
                }

            }
            Column(
                Modifier.weight(1f)
            ) {
                Row {
                    Bar(Modifier.fillMaxWidth())
                }
                Box {
                    Surface(
                        Modifier.defaultMinSize(
                            HORIZONTAL_BAR_HEIGHT,
                            HORIZONTAL_BAR_HEIGHT),
                        color = Gold
                    ) {
                        Box(
                            Modifier
                                .defaultMinSize(
                                    CONTENT_CORNER_RADIUS,
                                    CONTENT_CORNER_RADIUS)
                                .background(
                                    Black,
                                    RoundedCornerShape(
                                        topStart = CONTENT_CORNER_RADIUS,
                                        topEnd = 0.dp,
                                        bottomStart = 0.dp,
                                        bottomEnd = 0.dp
                                    )
                                )
                        ) {

                        }
                    }
                    Column(
                        Modifier.fillMaxWidth().padding(CONTENT_PADDING)
                    ) {
                        Text(
                            "Unidentified vessel travelling at sub warp speed, bearing 235.7." +
                            " Fluctuations in energy readings from it, Captain. All transporters off. " +
                            "A strange set-up, but I'd say the graviton generator is depolarized. " +
                            "The dark colourings of the scrapes are the leavings of natural rubber," +
                            " a type of non-conductive sole used by researchers experimenting with electricity." +
                            " The molecules must have been partly de-phased by the anyon beam.",
                            color = SpaceWhite)
                    }
                }
                Row {
                    Bar(Modifier.fillMaxWidth())
                }
            }
        }
    }
}