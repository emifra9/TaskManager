package com.emilianofraile.taskmanager.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Long): Task

    suspend fun addTask(task: Task)

    suspend fun removeTask(task: Task)

    suspend fun updateTask(task: Task)
}
