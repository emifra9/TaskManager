package com.emilianofraile.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emilianofraile.taskmanager.ui.screen.NavigationEvent
import com.emilianofraile.taskmanager.ui.screen.TaskEvent
import com.emilianofraile.taskmanager.ui.screen.TaskFormScreen
import com.emilianofraile.taskmanager.ui.screen.TaskFormState
import com.emilianofraile.taskmanager.ui.screen.TaskListScreen
import com.emilianofraile.taskmanager.ui.screen.TaskUiState

sealed class Screen(val route: String) {
    data object TaskList : Screen("task_list")
    data object TaskForm : Screen("task_form/{taskId}") {
        fun createRoute(taskId: Long = 0L) = "task_form/$taskId"
    }
}

@Composable
fun TaskNavigation(
    navController: NavHostController,
    state: TaskUiState,
    formState: TaskFormState,
    onTaskListEvent: (TaskEvent) -> Unit,
    onTaskFormEvent: (TaskEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit = { event -> navController.handleNavigationEvent(event, onTaskFormEvent) }
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TaskList.route
    ) {
        composable(Screen.TaskList.route) {
            LaunchedEffect(Unit) {
                onTaskFormEvent(TaskEvent.ResetForm)
            }
            TaskListScreen(
                state = state,
                onEvent = onTaskListEvent,
                onNavigationEvent = onNavigationEvent
            )
        }

        composable(
            route = Screen.TaskForm.route
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull() ?: 0L
            
            LaunchedEffect(taskId) {
                if (taskId > 0L) {
                    onTaskListEvent(TaskEvent.GetTask(taskId))
                }
            }

            TaskFormScreen(
                state = formState,
                onEvent = onTaskFormEvent,
                onNavigationEvent = onNavigationEvent
            )
        }
    }
}

fun NavHostController.handleNavigationEvent(
    event: NavigationEvent,
    onTaskFormEvent: (TaskEvent) -> Unit
) {
    when (event) {
        is NavigationEvent.NavigateToAdd -> navigate(Screen.TaskForm.createRoute())
        is NavigationEvent.NavigateToEdit -> navigate(Screen.TaskForm.createRoute(event.id))
        is NavigationEvent.NavigateBack -> {
            onTaskFormEvent(TaskEvent.ResetForm)
            popBackStack()
        }
    }
} 