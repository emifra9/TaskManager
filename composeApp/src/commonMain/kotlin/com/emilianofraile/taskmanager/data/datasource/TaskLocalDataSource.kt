package com.emilianofraile.taskmanager.data.datasource

import com.emilianofraile.taskmanager.data.Task
import kotlinx.coroutines.flow.Flow


interface TaskLocalDataSource {
    suspend fun getAllTasks(): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task?
    suspend fun createTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}