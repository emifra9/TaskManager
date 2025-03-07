package com.emilianofraile.taskmanager.di

import com.emilianofraile.taskmanager.data.TaskRepository
import com.emilianofraile.taskmanager.data.datasource.TaskLocalDataSource
import com.emilianofraile.taskmanager.data.datasource.TaskLocalDataSourceImpl
import com.emilianofraile.taskmanager.data.TaskRepositoryImpl
import com.emilianofraile.taskmanager.ui.viewmodel.TaskViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.compose.viewmodel.dsl.viewModelOf


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule() , platformModule(),
            provideDataSourceModule, provideRepositoryModule,
            provideViewModelModule, platformModule()
        )
    }

val provideDataSourceModule = module {
    singleOf(::TaskLocalDataSourceImpl).bind(TaskLocalDataSource::class)
}
val provideRepositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
   // singleOf(::TaskRepositoryImpl).bind(TaskRepository::class)
}
val provideViewModelModule = module {
    viewModelOf(::TaskViewModel)
}
