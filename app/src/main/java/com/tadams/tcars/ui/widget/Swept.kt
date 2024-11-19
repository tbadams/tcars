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
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.CONTENT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.CONTENT_PADDING
import com.tadams.tcars.ui.theme.HORIZONTAL_BAR_HEIGHT
import com.tadams.tcars.ui.theme.INTER_FRAME_GAP
import com.tadams.tcars.ui.theme.INTRA_FRAME_GAP
import com.tadams.tcars.ui.theme.SURFACE_PADDING
import com.tadams.tcars.ui.theme.SWEPT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.SpaceWhite
import com.tadams.tcars.ui.theme.TCARSTheme

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


@Preview
@Composable
fun PreviewSwept() {
    TCARSTheme {
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .padding(top = INTER_FRAME_GAP / 2)
        ) {
            Column(
                Modifier.width(IntrinsicSize.Max)
            ) {
                Bar(
                    shape = RoundedCornerShape(topStart = SWEPT_CORNER_RADIUS),
                    alignment = Alignment.BottomEnd
                ) {
                    Text(
                        "HELLO WORLD",
                        Modifier.padding(
                            top = SWEPT_CORNER_RADIUS - SURFACE_PADDING)
                    )
                }
                Spacer(Modifier.height(INTRA_FRAME_GAP))
                Bar(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {

                }
                Spacer(Modifier.height(INTRA_FRAME_GAP))
                Bar(
                    Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(bottomStart = SWEPT_CORNER_RADIUS),
                    alignment = Alignment.TopEnd
                ) {
                    Text(
                        "YO",
                        Modifier.padding(
                            bottom = SWEPT_CORNER_RADIUS - SURFACE_PADDING)
                    )
                }

            }
            Column(
                Modifier.weight(1f)
            ) {
                Row {
                    Bar(Modifier.fillMaxWidth())
                }
                Box (
                    Modifier.background(MaterialTheme.colorScheme.surface)
                ) {
                    Box(
                        Modifier
                            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(
                                topStart = CONTENT_CORNER_RADIUS,
                                bottomStart = CONTENT_CORNER_RADIUS
                            ))
                            .fillMaxWidth()
                            .padding(CONTENT_PADDING)
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