package com.cryptoreal.app

import android.app.Application
import com.cryptoreal.app.di.modules.appModules
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModules
            )
        }
    }
}