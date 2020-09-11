package kz.maestrosultan.tidaltask.app

import android.app.Application
import kz.maestrosultan.tidaltask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TidalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    private fun initApp() {
        startKoin {
            androidContext(this@TidalApplication)
            modules(modules = appModule)
        }
    }
}