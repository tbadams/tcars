package com.tadams.tcars.app.model

sealed class SystemType {
    object Ship : SystemType()
    object Power : SystemType()
    object Engine : SystemType()
    object Environment : SystemType()
    object Fields : SystemType()
    object Weapon : SystemType()
}

interface System {

}