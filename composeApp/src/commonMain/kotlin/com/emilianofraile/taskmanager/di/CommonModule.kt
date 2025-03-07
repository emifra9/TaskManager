package com.emilianofraile.taskmanager.di

import com.emilianofraile.taskmanager.data.AppDatabase
import com.emilianofraile.taskmanager.data.TaskDao
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(): Module = module {
    single<TaskDao> { get<AppDatabase>().getDao() }
}