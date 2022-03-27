object Config {

    const val versionName = "0.1.0" // X.Y.Z; X = Major, Y = Minor, Z = Patch level
    private const val versionCodeBase = 101000 // XYYZZM; M = Module (tv, mobile)
    val versionCodeMobile = versionCodeBase + 3

    const val BUILD_TOOLS_VERSION = "32.0.0"
    const val COMPILE_SDK = 32
    const val TARGET_SDK = 32
    const val MIN_SDK = 23

    const val ANDROID_GRADLE_PLUGIN = "7.1.2"
    const val KOTLIN = "1.6.10"
    const val COMPOSE = "1.2.0-alpha01"
}