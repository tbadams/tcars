package com.tadams.tcars.app.ui

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tadams.tcars.app.model.ShuttleCluster
import com.tadams.tcars.app.model.System

class SystemsViewModel: ViewModel() {
    val clusters = ShuttleCluster.map {
        it.systems.map {
            System(it)
        }
    }
    val selectedCluster = mutableIntStateOf(0)

    val systems: List<System>
        get() = clusters.flatten()

    private val drawSystems: List<System>
        get() = systems.filter { it.info.powerDraw >= 0 }

    val genSystems: List<System>
        get() = systems.filter { it.info.powerDraw < 0}

    val maxLoad: Int
        get() = drawSystems.sumOf { it.info.maxPowerDraw }

    val maxGen: Int
        get() = genSystems.sumOf { -it.info.powerDraw }

    val power: ShipPower
        get() = ShipPower(
            supply = genSystems.sumOf { -it.curDraw },
            maxSupply = genSystems.sumOf { -it.info.maxPowerDraw },
            protectedLoad = drawSystems.filter { it.protected.value }.sumOf { it.curDraw },
            unprotectedLoad = drawSystems.filter { !it.protected.value }.sumOf { it.curDraw },
            maxLoad = maxLoad
        )
}