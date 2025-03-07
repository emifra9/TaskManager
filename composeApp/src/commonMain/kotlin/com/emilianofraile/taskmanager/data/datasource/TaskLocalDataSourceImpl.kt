package com.emilianofraile.taskmanager.data.datasource

import com.emilianofraile.taskmanager.data.AppDatabase
import com.emilianofraile.taskmanager.data.Task
import kotlinx.coroutines.flow.Flow

class TaskLocalDataSourceImpl(private val appDatabase: AppDatabase) : TaskLocalDataSource {
    override suspend fun getAllTasks(): Flow<List<Task>> = appDatabase.getDao().getAllTasks()

    override suspend fun getTaskById(id: Long): Task = appDatabase.getDao().getTaskById(id)

    override suspend fun createTask(task: Task) = appDatabase.getDao().insertAllTasks(task)

    override suspend fun deleteTask(task: Task) = appDatabase.getDao().deleteTask(task = task)

    override suspend fun updateTask(task: Task) = appDatabase.getDao().updateTask(task)

}