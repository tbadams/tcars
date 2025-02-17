@file:OptIn(ExperimentalMaterial3Api::class)

package com.tadams.tcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tadams.tcars.app.model.ID_POWER
import com.tadams.tcars.app.model.ShuttleCluster
import com.tadams.tcars.app.ui.PowerWidget
import com.tadams.tcars.app.ui.SystemCommon
import com.tadams.tcars.app.ui.SystemsViewModel
import com.tadams.tcars.ui.theme.TCARSTheme
import com.tadams.tcars.ui.theme.tcarsSubheader
import com.tadams.tcars.ui.widget.Bar
import com.tadams.tcars.ui.widget.BarButton
import com.tadams.tcars.ui.widget.BarColumn
import com.tadams.tcars.ui.widget.BarFrame
import com.tadams.tcars.ui.widget.Button
import com.tadams.tcars.ui.widget.ButtonDefaults
import com.tadams.tcars.ui.widget.ProgressBar
import com.tadams.tcars.ui.widget.SeekBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TCARSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(modifier: Modifier = Modifier) {
    val viewModel = SystemsViewModel()
    Column(modifier = modifier) {
        BarFrame( // Header Frame
            startColumn = {
                BarColumn(
                    true,
                    topContent = null
                )
            },
            topRow = null,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Spacer(modifier.weight(1f))
                Button({}) {
                    Text("SHP")
                }
                Button({}) {
                    Text("SYS")
                }
                Button({}) {
                    Text("COM")
                }
            }
        }
        BarFrame( // Content Frame
            Modifier.weight(1f),
            startColumn = {
                BarColumn(true) {
                    ShuttleCluster.forEachIndexed { index, it ->
                        BarButton(
                            {
                                viewModel.selectedCluster.intValue = index
                            },
                            Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (index == viewModel.selectedCluster.intValue)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(it.displayName)
                        }
                    }
                    Bar(Modifier
                        .fillMaxWidth()
                        .weight(1f))
                }
            },
            bottomRow = {
                Bar(Modifier.fillMaxHeight().weight(1f))
                Text(
                    "CREW 3",
                    Modifier.padding(start = 4.dp, end = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Bar(Modifier.fillMaxHeight())
                Text(
                    "HULL 100",
                    Modifier.padding(start = 4.dp, end = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Bar(Modifier.fillMaxHeight())
                Text(
                    "SHIELD 100",
                    Modifier.padding(start = 4.dp, end = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Bar(Modifier.fillMaxHeight())
            }
        ) {
            val ss = rememberScrollState()
            Column(
                modifier.verticalScroll(ss)
            ) {
                val cluster = viewModel.clusters[viewModel.selectedCluster.intValue]
               val power = viewModel.power
                if (ShuttleCluster[viewModel.selectedCluster.intValue].id == ID_POWER) {
                    PowerWidget(power)
                    Spacer(Modifier.height(8.dp))
                }
                cluster.forEach {
                    SystemCommon(
                        it.info.name,
                        it.protected,
                        it.info.description,
                        powerSetting = it.powerSetting,
                        powerDraw = it.info.powerDraw,
                        powerMax = it.info.maxPowerDraw,
                        powerActual = (if (it.protected.value) {
                            viewModel.power.protectedRatio * it.curDraw
                        } else {
                            viewModel.power.unprotectedRatio * it.curDraw
                        }).toInt(),
                        noSafety = it.info.powerDraw < 0
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview()
@Composable
private fun ContentPreview() {
    TCARSTheme {
        Content()
    }
}

@Preview()
@Composable
private fun TestContent() {
    Text(
        "Header".uppercase(),
        Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.tcarsSubheader()
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text =             "Exceeding reaction chamber thermal limit. We have begun power-supply " +
            "calibration. Force fields have been established on all turbo lifts" +
            " and crawlways. Computer, run a level-two diagnostic on warp-drive" +
            " systems. Antimatter containment positive. Warp drive within" +
            " normal parameters. I read an ion trail characteristic of a " +
            "freighter escape pod."
    )
    Spacer(Modifier.height(12.dp))
    Row(verticalAlignment = Alignment.Bottom) {
        ProgressBar({0.15f}, Modifier.weight(1f))
        Text(
            "15",
            Modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }

    Spacer(Modifier.height(4.dp))
    ProgressBar({0.55f}, Modifier.fillMaxWidth())
    Spacer(Modifier.height(4.dp))
    ProgressBar({1f}, Modifier.fillMaxWidth())
    Spacer(Modifier.height(4.dp))
    ProgressBar({0f}, Modifier.fillMaxWidth())
    val seekVal = remember { mutableStateOf(0.3f) }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        SeekBar(seekVal.value, {seekVal.value = it}, Modifier.weight(1f))
        Text(
            "${(seekVal.value * 100).toInt()}",
            Modifier.padding(start = 4.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
    Spacer(Modifier.height(4.dp))
}