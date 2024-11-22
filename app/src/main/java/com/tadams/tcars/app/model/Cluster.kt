package com.tadams.tcars.app.model

data class Cluster (
    val id: String,
    val displayName: String,
    val systems: List<SystemInfo>
)

val ShuttleCluster = listOf(
//    Cluster("ship", "SHIP"),
    Cluster(
        "power",
        "POWER",
        listOf(
            SystemInfo(
                SystemType.Power,
                "Impulse Fusion Core",
                -755,
                maxPowerDraw = -755
            ),
            SystemInfo(
                SystemType.Power,
                "Warp Antimatter Reactor",
                -9800,
                maxPowerDraw = -9800
            )
        )
    ),
    Cluster(
        "engine",
        "ENGINE",
        listOf(
            SystemInfo(
                SystemType.Impulse,
                "Impulse Engines",
                525
            ),
            SystemInfo(
                SystemType.WarpCore,
                "Warp Core",
                7500
            ),
            SystemInfo(
                SystemType.WarpCore,
                "Ramscoop",
                110
            ),
            SystemInfo(
                SystemType.WarpCore,
                "Pump Systems",
                400
            )
        )
    ),
    Cluster(
        "env",
        "ENV",
        listOf(
            SystemInfo(
                SystemType.Environment,
                "Life Support",
                482,
                true
            ),
            SystemInfo(
                SystemType.Gravity,
                "Artifical Gravity",
                444
            ),
            SystemInfo(
                SystemType.Environment,
                "Inertial Dampers",
                600
            ),
            SystemInfo(
                SystemType.Power,
                "Core Electronics",
                94
            )

        )
    ),
    Cluster(
        "fields",
        "FIELDS",
        listOf(
            SystemInfo(
                SystemType.Shield,
                "Deflector Shields",
                1100,
                true
            )
        )
    ),
    Cluster(
        "weapon",
        "WEAPON",
        listOf(
            SystemInfo(
                SystemType.Phaser,
                "Category 4 Phaser Bank",
                960
            )
        )
    )
)