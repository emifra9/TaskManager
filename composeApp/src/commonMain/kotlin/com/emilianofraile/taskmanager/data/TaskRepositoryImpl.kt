package com.emilianofraile.taskmanager.data

import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl (private val appDatabase: AppDatabase) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> = appDatabase.getDao().getAllTasks()

    override suspend fun getTaskById(id: Long): Task = appDatabase.getDao().getTaskById(id)

    override suspend fun addTask(task: Task) = appDatabase.getDao().insertAllTasks(task)

    override suspend fun removeTask(task: Task) = appDatabase.getDao().deleteTask(task)

    override suspend fun updateTask(task: Task) = appDatabase.getDao().updateTask(task)

}
        
