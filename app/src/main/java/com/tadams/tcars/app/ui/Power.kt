package com.tadams.tcars.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tadams.tcars.ui.theme.TCARSTheme
import com.tadams.tcars.ui.widget.ProgressBar
import kotlin.math.max
import kotlin.math.min

@Composable
fun PowerSystemBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

    }
}

data class ShipPower(
    val supply: Int,
    val maxSupply: Int,
    val protectedLoad: Int,
    val unprotectedLoad: Int,
    val maxLoad: Int,
) {
    val totalLoad: Int
        get() = protectedLoad + unprotectedLoad
    val protectedSupply: Int
        get() = if (supply < protectedLoad) supply else protectedLoad
    val unprotectedSupply: Int
        get() = if (protectedSupply < protectedLoad) 0 else min(supply - protectedLoad, unprotectedLoad)
    val surplus: Int
        get() = max(supply - totalLoad, 0)
    val protectedRatio: Float
        get() = protectedSupply.toFloat() / protectedLoad.toFloat()
    val unprotectedRatio: Float
        get() = unprotectedSupply.toFloat() / unprotectedLoad.toFloat()
}

@Composable
fun PowerWidget(
    power: ShipPower,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
    ) {
        ProgressBar(
            { power.supply.toFloat() / power.maxSupply.toFloat() },
            "${power.supply} / ${power.maxSupply}",
            startLabel = "OUT",
        )
        ProgressBar(
            { power.totalLoad.toFloat() / power.maxLoad.toFloat()},
            "${power.totalLoad} / ${power.maxLoad}",
            startLabel = "LOAD",
        )
        ProgressBar(
            { power.protectedSupply.toFloat() / power.protectedLoad.toFloat()},
            "${power.protectedSupply} / ${power.protectedLoad}",
            startLabel = "PROT",
        )
        ProgressBar(
            { power.unprotectedSupply.toFloat() / power.unprotectedLoad.toFloat()},
            "${power.unprotectedSupply} / ${power.unprotectedLoad}",
            startLabel = "FREE",
        )
        ProgressBar(
            { power.surplus.toFloat() / power.maxLoad.toFloat()},
            "${power.surplus}",
            startLabel = "RSRV",
        )
    }
}

@Preview
@Composable
fun PowerWidgetPreview() {
    TCARSTheme(

    ) {
        PowerWidget(
            power = ShipPower(
                supply = 100,
                maxSupply = 110,
                protectedLoad = 10,
                unprotectedLoad = 50,
                maxLoad = 160
            )
        )
    }
}