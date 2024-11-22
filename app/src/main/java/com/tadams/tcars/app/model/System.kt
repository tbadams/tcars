package com.tadams.tcars.app.model

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf

sealed class SystemType {
    object Ship : SystemType()
    object Power : SystemType()
    object Impulse : SystemType()
    object WarpCore : SystemType()
    object Environment : SystemType()
    object Gravity : SystemType()
    object Shield : SystemType()
    object Phaser : SystemType()
}

data class SystemInfo(
    val type: SystemType,
    val name: String,
    val powerDraw: Int,
    val startProtected: Boolean = false,
    val defaultPowerSetting: Float = 1.0f,
    val description: String = "It indicates a synchronic distortion in the areas emanating triolic" +
        " waves. The cerebellum, the cerebral cortex, the brain stem," +
        "  the entire nervous system has been depleted of electrochemical " +
        "energy. Any device like that would produce high levels of triolic" +
        " waves. These walls have undergone some kind of selective " +
        "molecular polarization. I haven't determined if our phaser " +
        "energy can generate a stable field. We could alter the photons " +
        "with phase discriminators.\n",
    val maxPowerDraw: Int = 2 * powerDraw
) {

}

data class System(
    val info: SystemInfo,
    val powerSetting: MutableState<Float> = mutableFloatStateOf(info.defaultPowerSetting),
    val protected: MutableState<Boolean> = mutableStateOf(info.startProtected),
) {
    val curDraw: Int
        get() = (powerSetting.value * info.powerDraw).toInt()
}