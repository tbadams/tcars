package com.tadams.tcars.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.BIG_BUTTON_WIDTH
import com.tadams.tcars.ui.theme.HORIZONTAL_BAR_HEIGHT
import com.tadams.tcars.ui.theme.SURFACE_PADDING
import com.tadams.tcars.ui.theme.TCARSTheme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = androidx.compose.material3.ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.padding(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val containerColor = if(enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if(enabled) colors.contentColor else colors.disabledContentColor
    Surface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = border,
        interactionSource = interactionSource
    ) {
        ProvideContentColorTextStyle(
            contentColor = contentColor,
            textStyle = MaterialTheme.typography.labelLarge) {
            Row(
                Modifier
                    .defaultMinSize(
                        minWidth = BIG_BUTTON_WIDTH,
                        minHeight = 58.dp
                    )
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
                content = content
            )
        }
    }
}

@Composable
fun BarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(
        SURFACE_PADDING
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(0.dp),
        colors = colors,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

@Composable
fun SmallButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.padding(vertical = 4.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(HORIZONTAL_BAR_HEIGHT, HORIZONTAL_BAR_HEIGHT),
        enabled = enabled,
        shape = CircleShape,
        colors = colors,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        content()
    }
}

object ButtonDefaults {
    val ButtonVerticalPadding = 8.dp
    val ButtonHorizontalPadding = 16.dp

    @Composable
    fun padding(
        horizontal:Dp = ButtonHorizontalPadding,
        vertical:Dp = ButtonVerticalPadding,
    ) = PaddingValues(horizontal, vertical, horizontal, vertical)

    @Composable
    fun buttonColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ) = androidx.compose.material3.ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

}

@Composable
internal fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit
) {
    val mergedStyle = LocalTextStyle.current.merge(textStyle)
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle,
        content = content
    )
}

@Preview
@Composable
fun PreviewButton () {
    TCARSTheme {
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text("BUTTON")
        }
    }
}

@Preview
@Composable
fun PreviewBarButton () {
    TCARSTheme {
        BarButton(
            onClick = { /*TODO*/ }
        ) {
            Text("BUTTON")
        }
    }
}

@Preview
@Composable
fun PreviewSmallButton () {
    TCARSTheme {
        SmallButton(
            onClick = { /*TODO*/ }
        ) {
            Text("BUTTON")
        }
    }
}