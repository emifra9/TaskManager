package com.emilianofraile.taskmanager.ui.screen

data class TaskFormState(
    val taskId: Long = 0L,
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val isCompleted: Boolean = false,
    val error: String? = null
) 