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
                maxPowerDraw = -755,
                description = "The Aberash-Makkonen sublight pulse drive consists of a small fusion reactor, 2 primary thrust ports and 16 Reaction Control System nozzles for fine maneuvering. In addition to powering ships without warp cores, electroplasma can be passed through a subspace filter and vented to generate momentum. A status bar displays the the current flight pattern, and when enabled by the pilot, suggestions can be made."
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
                525,
                description = "The Aberash-Makkonen sublight pulse drive consists of a small fusion reactor, 2 primary thrust ports and 16 Reaction Control System nozzles for fine maneuvering. In addition to powering ships without warp cores, electroplasma can be passed through a subspace filter and vented to generate momentum. A status bar displays the the current flight pattern, and when enabled by the pilot, suggestions can be made."

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
                true,
                description = "Life Support systems ensure breathable air and livable temperature throughout a vessel.  Due to its importance, this system’s power safety is enabled by default."
            ),
            SystemInfo(
                SystemType.Gravity,
                "Artifical Gravity",
                444,
                description = "Sometimes considered part of Life Support, the artificial gravity generator simulates the 1G environment most humans are comfortable with; reducing the gravity setting my impact mobility and performance of crew.  Reducing power draw will proportionately translate into a lower gravity (as would increasing it, theoretically). Safety protocol requires the gravity alarm be sounded for at least fifteen seconds prior to disabling or enabling gravity."
            ),
            SystemInfo(
                SystemType.Environment,
                "Inertial Dampers",
                600,
                true,
                description = "Inertial damping generator leverages subspace to protect the starship interior from the extreme acceleration forces acting on the vessel.  Do not under any circumstances disable this system.  You will regret it."
            ),
            SystemInfo(
                SystemType.Power,
                "Core Electronics",
                94,
                true,
                description = "Mundane but important power draw for the ship's computer, " +
                    "interactive consoles, lighting, etc. The system is fairly fault tolerant," +
                    "however care must be taken never to completely disable it, or the " +
                    "computer interface necessary to restore power will be disabled."
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
                960,
                description = "The Category 4 phaser is a low power energy weapon intended for use" +
                    " on small landing craft and picket ships. A dedicated plasma buffer gives the" +
                    " weapon a constant power draw. The beam phase frequency can be adjusted " +
                    "between <> and <>, however values outside of the optimum <>-<> band may " +
                    "produce inferior or unpredictable results."
            )
        )
    )
)