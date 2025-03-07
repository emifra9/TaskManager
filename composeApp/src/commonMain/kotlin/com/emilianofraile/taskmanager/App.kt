package com.emilianofraile.taskmanager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.emilianofraile.taskmanager.navigation.TaskNavigation
import com.emilianofraile.taskmanager.ui.theme.AppTheme
import com.emilianofraile.taskmanager.ui.viewmodel.TaskViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<TaskViewModel>()
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val formState = viewModel.formState.collectAsState().value

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TaskNavigation(
                navController = navController,
                state = state,
                formState = formState,
                onTaskListEvent = viewModel::onEvent,
                onTaskFormEvent = { event ->
                    viewModel.onEvent(event)
                }
            )
        }
    }
}