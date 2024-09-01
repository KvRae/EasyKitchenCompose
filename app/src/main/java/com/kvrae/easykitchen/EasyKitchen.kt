package com.kvrae.easykitchen

import android.app.Application
import com.kvrae.easykitchen.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class EasyKitchen: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EasyKitchen)
            modules(appModule)
        }
    }
}