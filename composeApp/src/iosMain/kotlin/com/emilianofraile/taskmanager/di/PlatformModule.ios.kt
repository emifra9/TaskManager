package com.emilianofraile.taskmanager.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.emilianofraile.taskmanager.data.AppDatabase
import com.emilianofraile.taskmanager.data.TaskDao
import com.emilianofraile.taskmanager.data.getRoomDatabase
import com.emilianofraile.taskmanager.db.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { 
        getRoomDatabase(getDatabaseBuilder())
    }
    single<TaskDao> { get<AppDatabase>().getDao() }
}
