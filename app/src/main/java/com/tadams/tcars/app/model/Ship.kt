package com.tadams.tcars.app.model

data class ShipState(
    val hull: Int, // 0-100
    val shields: Int, // 0-100
    val crew: Int,
)