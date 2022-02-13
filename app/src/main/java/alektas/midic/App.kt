package alektas.midic

import alektas.midic.di.AppComponent
import alektas.midic.di.DaggerAppComponent
import android.app.Application

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create()
    }

}