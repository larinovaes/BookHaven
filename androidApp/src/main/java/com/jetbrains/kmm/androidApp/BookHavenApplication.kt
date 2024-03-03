package com.jetbrains.kmm.androidApp

import android.app.Application
import com.jetbrains.kmm.androidApp.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class BookHavenApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}
