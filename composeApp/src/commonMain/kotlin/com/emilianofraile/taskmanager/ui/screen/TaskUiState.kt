package com.emilianofraile.taskmanager.ui.screen

import com.emilianofraile.taskmanager.data.Task


sealed class TaskUiState {
    open val tasks: List<Task> = emptyList()

    data object Loading : TaskUiState()
    data object Empty : TaskUiState()
    data class Error(val message: String) : TaskUiState()
    data class Success(override val tasks: List<Task>) : TaskUiState()
}

sealed class TaskEvent {
    data class SaveTask(
        val title: String, 
        val description: String
    ) : TaskEvent()
    data class RemoveTask(val task: Task) : TaskEvent()
    data class EditTask(val task: Task) : TaskEvent()
    data class GetTask(val id: Long) : TaskEvent()
    data object ResetForm : TaskEvent()
}

sealed class NavigationEvent {
    data class NavigateToEdit(val id: Long) : NavigationEvent()
    data object NavigateToAdd : NavigationEvent()
    data object NavigateBack : NavigationEvent()
}