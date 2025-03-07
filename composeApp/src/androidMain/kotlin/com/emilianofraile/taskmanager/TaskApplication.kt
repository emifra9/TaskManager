package com.emilianofraile.taskmanager

import android.app.Application
import com.emilianofraile.taskmanager.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class TaskApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            appDeclaration = { androidContext(this@TaskApplication) },
        )
    }
}
