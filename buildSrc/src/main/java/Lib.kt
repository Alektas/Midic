object Lib {
    object Common {
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx"
        const val APP_COMPAT = "androidx.appcompat:appcompat"
        const val CORE_KTX = "androidx.core:core-ktx"
        const val LIFECYCLE_VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx"
        const val MATERIAL = "com.google.android.material:material"
        const val INJECT = "javax.inject:javax.inject"
    }
    object Kotlin {
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test"
    }
    object Dagger {
        const val CORE = "com.google.dagger:dagger"
        const val COMPILER = "com.google.dagger:dagger-compiler"
    }
    object DataStore {
        const val TYPED = "androidx.datastore:datastore"
        const val PREFERENCES = "androidx.datastore:datastore-preferences"
    }
    object Room {
        const val RUNTIME = "androidx.room:room-runtime"
        const val KTX = "androidx.room:room-ktx"
        const val COMPILER = "androidx.room:room-compiler"
    }
    object Retrofit {
        const val CORE = "com.squareup.retrofit2:retrofit"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
    }
    object Compose {
        const val ACTIVITY = "androidx.activity:activity-compose"
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-compose"
        const val MATERIAL2 = "androidx.compose.material:material"
        const val MATERIAL3 = "androidx.compose.material3:material3"
        const val RUNTIME = "androidx.compose.runtime:runtime"
        const val ANIMATION = "androidx.compose.animation:animation"
        const val TOOLING = "androidx.compose.ui:ui-tooling"
        const val NAVIGATION = "androidx.navigation:navigation-compose"
        const val ACCOMPANIST_PAGER = "com.google.accompanist:accompanist-pager"
    }
    object Test {
        const val ARCH_CORE = "android.arch.core:core-testing"
        const val RUNNER = "androidx.test:runner"
        object JUnit {
            const val PLATFORM = "org.junit.platform:junit-platform-launcher"
            const val JUPITER = "org.junit.jupiter:junit-jupiter"
        }
        object Mockito {
            const val CORE = "org.mockito:mockito-core"
        }
        object Espresso {
            const val CORE = "androidx.test.espresso:espresso-core"
        }
    }
}