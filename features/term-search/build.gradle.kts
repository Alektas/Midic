plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        consumerProguardFiles.add(File("consumer-rules.pro"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

dependencies {

    api(platform(project(":depconstraints")))
    implementation(project(":core:theme"))
    implementation(project(":core:ui-components"))
    implementation(project(":core:ui-components:bottom-sheet"))
    implementation(project(":common"))

    implementation(Lib.Common.MATERIAL)
    implementation(Lib.Common.INJECT)
    implementation(Lib.Common.LIFECYCLE_VIEW_MODEL_KTX)

    implementation(Lib.Compose.RUNTIME)
    implementation(Lib.Compose.MATERIAL2)
    implementation(Lib.Compose.MATERIAL3)
    debugImplementation(Lib.Compose.TOOLING)
}
