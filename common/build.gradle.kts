plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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

    implementation(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))

    implementation(project(":core:theme"))
    implementation(project(":core:ui-components"))
    implementation(project(":core:utils:arch-base"))

    implementation(Lib.Common.MATERIAL)
    implementation(Lib.Common.INJECT)

    implementation(Lib.Compose.RUNTIME)
    implementation(Lib.Compose.MATERIAL2)
    implementation(Lib.Compose.MATERIAL3)
    implementation(Lib.Compose.TOOLING_PREVIEW)
    debugImplementation(Lib.Compose.TOOLING)

    implementation(Lib.Retrofit.CORE)
    implementation(Lib.Retrofit.CONVERTER_GSON)

    implementation(Lib.Room.KTX)
    kapt(Lib.Room.COMPILER)

    implementation(Lib.ImageLoading.COIL)

}
