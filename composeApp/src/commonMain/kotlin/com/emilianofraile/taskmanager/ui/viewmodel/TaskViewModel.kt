package com.emilianofraile.taskmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emilianofraile.taskmanager.data.Task
import com.emilianofraile.taskmanager.data.TaskRepository
import com.emilianofraile.taskmanager.ui.screen.TaskEvent
import com.emilianofraile.taskmanager.ui.screen.TaskEvent.EditTask
import com.emilianofraile.taskmanager.ui.screen.TaskEvent.RemoveTask
import com.emilianofraile.taskmanager.ui.screen.TaskEvent.SaveTask
import com.emilianofraile.taskmanager.ui.screen.TaskFormState
import com.emilianofraile.taskmanager.ui.screen.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TaskViewModel() : ViewModel(), KoinComponent {
    private val repository: TaskRepository by inject()

    private val _state = MutableStateFlow<TaskUiState>(TaskUiState.Loading)
    val state: StateFlow<TaskUiState> = _state.asStateFlow()

    private val _formState = MutableStateFlow(TaskFormState())
    val formState: StateFlow<TaskFormState> = _formState.asStateFlow()

    init {
        observeTasks()
    }

    private fun resetFormState() {
        _formState.value = TaskFormState()
    }

    private fun observeTasks() {
        viewModelScope.launch() {
            repository.getAllTasks()
                .onStart { _state.value = TaskUiState.Loading }
                .catch { e -> _state.value = TaskUiState.Error(e.message ?: "Error desconocido") }
                .collect { tasks ->
                    _state.value = if (tasks.isEmpty()) {
                        TaskUiState.Empty
                    } else {
                        val sortedTasks = tasks.sortedByDescending { it.id }
                        TaskUiState.Success(sortedTasks)
                    }
                }
        }
    }

    fun onEvent(event: TaskEvent) {
        when (event) {
            is SaveTask -> addTask(
                title = event.title,
                description = event.description
            )
            is EditTask -> updateTask(event.task)
            is RemoveTask -> removeTask(event.task)
            is TaskEvent.GetTask -> loadTaskById(event.id)
            is TaskEvent.ResetForm -> resetFormState()
        }
    }

    private fun loadTaskById(id: Long) {
        viewModelScope.launch {
            try {
                _formState.value = _formState.value.copy(isLoading = true, error = null)
                val task = repository.getTaskById(id)
                _formState.value = _formState.value.copy(
                    taskId = id,
                    title = task.title,
                    description = task.description,
                    isLoading = false
                )
            } catch (e: Exception) {
                _formState.value = _formState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error al cargar la tarea"
                )
            }
        }
    }

    private fun addTask(title: String, description: String) {
        viewModelScope.launch() {
            try {
                val task = Task(
                    title = title,
                    description = description
                )
                repository.addTask(task)
                resetFormState()
            } catch (e: Exception) {
                _state.value = TaskUiState.Error(e.message ?: "Error al agregar tarea")
            }
        }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch() {
            try {
                repository.updateTask(task)
                resetFormState()
            } catch (e: Exception) {
                _state.value = TaskUiState.Error(e.message ?: "Error al actualizar tarea")
            }
        }
    }

    private fun removeTask(task: Task) {
        viewModelScope.launch() {
            try {
                repository.removeTask(task)
            } catch (e: Exception) {
                _state.value = TaskUiState.Error(e.message ?: "Error al eliminar tarea")
            }
        }
    }
} 