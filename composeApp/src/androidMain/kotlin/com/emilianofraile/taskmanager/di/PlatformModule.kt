package com.emilianofraile.taskmanager.di

import com.emilianofraile.taskmanager.data.AppDatabase
import com.emilianofraile.taskmanager.data.TaskDao
import com.emilianofraile.taskmanager.db.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AppDatabase> { 
        getDatabaseBuilder(get())
            .build()
    }
    single<TaskDao> { get<AppDatabase>().getDao() }
}