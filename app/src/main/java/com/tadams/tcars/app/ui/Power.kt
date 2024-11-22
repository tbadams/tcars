package com.tadams.tcars.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.math.max

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
        get() = max(supply - protectedSupply, 0)
    val protectedRatio: Float
        get() = protectedSupply.toFloat() / protectedLoad.toFloat()
    val unprotectedRatio: Float
        get() = unprotectedSupply.toFloat() / unprotectedLoad.toFloat()
}