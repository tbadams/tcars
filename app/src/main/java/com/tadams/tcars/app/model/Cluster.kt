package com.tadams.tcars.app.model

data class Cluster (
    val id: String,
    val displayName: String
)

val ShuttleCluster = listOf(
    Cluster("ship", "SHIP"),
    Cluster("power", "POWER"),
    Cluster("engine", "ENGINE"),
    Cluster("env", "ENV"),
    Cluster("fields", "FIELD"),
    Cluster("weapon", "WEAPON")
)