plugins {
    id("java-platform")
}

val kotlin = Config.KOTLIN
val coroutines = "1.6.0-native-mt"
val appCompat = "1.4.0"
val coreKtx = "1.7.0"
val activity = "1.4.0"
val lifecycle = "2.3.1"
val lifecycleViewModel = "2.4.0"
val dataStore = "1.0.0"
val workManager = "2.4.0"
val navigation = "2.4.0-rc01"
val room = "2.4.1"
val compose = Config.COMPOSE
val composeMaterial3 = "1.0.0-alpha03"
val composeActivity = "1.4.0"
val composeViewModel = "2.4.0"
val accompanist = "0.22.0-rc"
val material = "1.5.0"
val dagger = "2.40.5"
val retrofit = "2.9.0"
val okhttp = "5.0.0-alpha.3"
val mockito = "4.2.0"
val mockitoKotlin = "2.1.0"
val junitPlatform = "1.8.2"
val junitJupiter = "5.8.2"
val espresso = "3.4.0"
val testRunner = "1.4.0"
val archCoreTesting = "1.1.1"

dependencies {
    constraints {
        api("${Lib.Common.ACTIVITY_KTX}:$activity")
        api("${Lib.Common.APP_COMPAT}:$appCompat")
        api("${Lib.Common.CORE_KTX}:$coreKtx")
        api("${Lib.Common.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")
        api("${Lib.Common.MATERIAL}:$material")
        api("${Lib.Kotlin.STDLIB}:$kotlin")
        api("${Lib.Kotlin.COROUTINES}:$coroutines")
        api("${Lib.Kotlin.COROUTINES_TEST}:$coroutines")
        api("${Lib.Dagger.CORE}:$dagger")
        api("${Lib.Dagger.COMPILER}:$dagger")
        api("${Lib.DataStore.TYPED}:$dataStore")
        api("${Lib.DataStore.PREFERENCES}:$dataStore")
        api("${Lib.Room.KTX}:$room")
        api("${Lib.Room.RUNTIME}:$room")
        api("${Lib.Room.COMPILER}:$room")
        api("${Lib.Retrofit.CORE}:$retrofit")
        api("${Lib.Retrofit.CONVERTER_GSON}:$retrofit")
        api("${Lib.Retrofit.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Lib.Compose.ACTIVITY}:$composeActivity")
        api("${Lib.Compose.VIEW_MODEL}:$composeViewModel")
        api("${Lib.Compose.MATERIAL2}:$compose")
        api("${Lib.Compose.MATERIAL3}:$composeMaterial3")
        api("${Lib.Compose.RUNTIME}:$compose")
        api("${Lib.Compose.ANIMATION}:$compose")
        api("${Lib.Compose.TOOLING}:$compose")
        api("${Lib.Compose.NAVIGATION}:$navigation")
        api("${Lib.Compose.ACCOMPANIST_PAGER}:$accompanist")
        api("${Lib.Test.ARCH_CORE}:$archCoreTesting")
        api("${Lib.Test.RUNNER}:$testRunner")
        api("${Lib.Test.JUnit.PLATFORM}:$junitPlatform")
        api("${Lib.Test.JUnit.JUPITER}:$junitJupiter")
        api("${Lib.Test.Mockito.CORE}:$mockito")
        api("${Lib.Test.Espresso.CORE}:$espresso")
    }
}