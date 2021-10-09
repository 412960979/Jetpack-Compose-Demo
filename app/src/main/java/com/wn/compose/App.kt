package com.wn.compose

import android.app.Application
import com.wn.compose.di.networkModule
import com.wn.compose.di.repositoryModule
import com.wn.compose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}