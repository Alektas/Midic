import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdk = Config.COMPILE_SDK

    signingConfigs {
        create("release") {
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as? String
            keyAlias = keystoreProperties["keyAlias"] as? String
            keyPassword = keystoreProperties["keyPassword"] as? String
        }
    }
    defaultConfig {
        applicationId = "alektas.midic"
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK
        versionCode = Config.versionCodeMobile
        versionName = Config.versionName

        base.archivesName.set("Midic-${versionName}")
        signingConfig = signingConfigs.getByName("release")
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["room.incremental"] = "true"
                arguments["room.expandProjection"] = "true"
            }
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.COMPOSE
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    implementation(project(":core:theme"))
    implementation(project(":core:ui-components:bottom-sheet"))

    implementation(Lib.Kotlin.STDLIB)
    implementation(Lib.Kotlin.COROUTINES)
    implementation(Lib.Kotlin.COROUTINES_TEST)

    implementation(Lib.Common.APP_COMPAT)
    implementation(Lib.Common.CORE_KTX)
    implementation(Lib.Common.ACTIVITY_KTX)
    implementation(Lib.Common.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Lib.Common.MATERIAL)

    implementation(Lib.Compose.ACCOMPANIST_PAGER)
    implementation(Lib.Compose.RUNTIME)
    implementation(Lib.Compose.ANIMATION)
    implementation(Lib.Compose.MATERIAL3)
    implementation(Lib.Compose.ACTIVITY)
    implementation(Lib.Compose.VIEW_MODEL)
    implementation(Lib.Compose.NAVIGATION)
    debugImplementation(Lib.Compose.TOOLING)

    implementation(Lib.Dagger.CORE)
    kapt(Lib.Dagger.COMPILER)

    implementation(Lib.Retrofit.CORE)
    implementation(Lib.Retrofit.CONVERTER_GSON)
    implementation(Lib.Retrofit.OKHTTP_LOGGING_INTERCEPTOR)

    implementation(Lib.Room.RUNTIME)
    implementation(Lib.Room.KTX)
    kapt(Lib.Room.COMPILER)

    testImplementation(Lib.Test.ARCH_CORE)
    testImplementation(Lib.Test.RUNNER)
    testImplementation(Lib.Test.JUnit.PLATFORM)
    testImplementation(Lib.Test.JUnit.JUPITER)
    testImplementation(Lib.Test.Mockito.CORE)
    testImplementation(Lib.Test.Espresso.CORE)
}