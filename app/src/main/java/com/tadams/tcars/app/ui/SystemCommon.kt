package com.tadams.tcars.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tadams.tcars.ui.theme.Black
import com.tadams.tcars.ui.theme.Blue
import com.tadams.tcars.ui.theme.Green
import com.tadams.tcars.ui.theme.INTRA_FRAME_GAP
import com.tadams.tcars.ui.theme.Mars
import com.tadams.tcars.ui.theme.Orange
import com.tadams.tcars.ui.theme.SWEPT_CORNER_RADIUS
import com.tadams.tcars.ui.theme.TCARSTheme
import com.tadams.tcars.ui.theme.Yellow
import com.tadams.tcars.ui.theme.tcarsSubheader
import com.tadams.tcars.ui.widget.Bar
import com.tadams.tcars.ui.widget.ButtonDefaults
import com.tadams.tcars.ui.widget.ProgressBar
import com.tadams.tcars.ui.widget.SeekBar
import com.tadams.tcars.ui.widget.SmallButton
import com.tadams.tcars.ui.widget.WideBarFrame
import kotlin.math.abs

val FULL_POWER = 100
val MIN_TEMP = 1
val COLD_TEMP = 273 // 0C
val DEFAULT_TEMP = 293 // 20C
val LUKEWARM_TEMP = 313 // 40C
val WARM_TEMP = 333 // 60C
val HOT_TEMP = 353 // 80C
val MAX_TEMP = 500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemCommon(
    systemName: String,
    protected: MutableState<Boolean>,
    systemDescription: String,
    modifier: Modifier = Modifier,
    powerSetting: MutableState<Float> = mutableFloatStateOf(1f),
    powerMax: Int = 544,
    powerActual: Int = (powerSetting.value * powerMax).toInt(),
    temperature: Int = 173 + (powerSetting.value * 100).toInt(),
    noSafety: Boolean = false
) {
    Column(
        modifier
    ) {
        val showInfo = remember { mutableStateOf(false)}
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallButton(
                {
                    showInfo.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
            ) {
                Text("INFO")
            }
            Text(
                systemName.uppercase(),
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.tcarsSubheader(),
            )
        }
        if (showInfo.value) {
            Dialog(
                {
                    showInfo.value = false
                }
            ) {
                //(LocalView.current.parent as DialogWindowProvider).window.setDimAmount(1f)
                Card(
                    Modifier.padding(INTRA_FRAME_GAP),
                    shape = RoundedCornerShape(
                        SWEPT_CORNER_RADIUS,
                        SWEPT_CORNER_RADIUS,
                        0.dp,
                        0.dp
                    ),
                    colors = CardDefaults.cardColors(Black)
                ) {
                    WideBarFrame(
                        startColumn = {
                            Bar(Modifier.weight(1f))
                            Bar(shape = RoundedCornerShape(
                                0,
                                0,
                                50,
                                50
                            ))
                        },
                        endColumn = {
                            Bar(Modifier.weight(1f))
                            Bar(shape = RoundedCornerShape(
                                0,
                                0,
                                50,
                                50
                            ))
                        }
                    ) {
                        Text(
                            systemDescription,
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 24.dp)
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(
                Modifier.weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        "%",
                        Modifier.padding(end = 8.dp).defaultMinSize(27.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    SeekBar(
                        powerSetting.value,
                        {powerSetting.value = it },
                        Modifier.weight(1f),
                        steps = 39,
                        valueRange = 0f..2f
                    )
                    Text(
                        "${(powerSetting.value * FULL_POWER).toInt()}",
                        Modifier.padding(start = 4.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "LOAD",
                        Modifier.padding(end = 8.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    ProgressBar(
                        {powerActual.toFloat() / powerMax},
                        Modifier.weight(1f)
                    )
                    Text(
                        "${abs(powerActual)}",
                        Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            if (!noSafety) {
                Spacer(Modifier.width(8.dp))
                SmallButton(
                    {protected.value = !protected.value},
                    Modifier
                        .semantics { role = Role.Checkbox }
                        .toggleable(
                            protected.value,
                            enabled = true,
                            role = Role.Checkbox,
                            onValueChange = {}
                        )
                    ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (protected.value) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else MaterialTheme.colorScheme.surfaceVariant,
                    )
                ) {
                    Text("SAFETY")
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                "TEMP",
                Modifier.padding(end = 8.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            ProgressBar(
                {temperature.toFloat() / MAX_TEMP},
                Modifier.weight(1f),
                color = tempColor(temperature)
            )
            Text(
                "$temperature",
                Modifier.padding(start = 8.dp),
                color = tempColor(temperature)
            )
        }
    }
}

fun tempColor(temp: Int): Color {
    return when {
        temp < COLD_TEMP -> Blue
        temp <= DEFAULT_TEMP -> Green
        temp <= WARM_TEMP -> Yellow
        temp <= HOT_TEMP -> Orange
        else -> Mars
    }
}

@Preview
@Composable
fun SystemCommonPreview() {
    TCARSTheme {
        val powerSetting = remember { mutableFloatStateOf(1f)}
        SystemCommon(
            "System Name",
            remember { mutableStateOf(false) },
            "It indicates a synchronic distortion in the areas emanating triolic" +
                " waves. The cerebellum, the cerebral cortex, the brain stem," +
                "  the entire nervous system has been depleted of electrochemical " +
                "energy. Any device like that would produce high levels of triolic" +
                " waves. These walls have undergone some kind of selective " +
                "molecular polarization. I haven't determined if our phaser " +
                "energy can generate a stable field. We could alter the photons " +
                "with phase discriminators.\n"
            )

    }
}