package com.emilianofraile.taskmanager.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emilianofraile.taskmanager.data.Task
import com.emilianofraile.taskmanager.ui.screen.NavigationEvent.NavigateToAdd
import com.emilianofraile.taskmanager.ui.screen.TaskEvent.RemoveTask
import com.emilianofraile.taskmanager.ui.screen.TaskUiState.Empty
import com.emilianofraile.taskmanager.ui.screen.TaskUiState.Error
import com.emilianofraile.taskmanager.ui.screen.TaskUiState.Loading
import com.emilianofraile.taskmanager.ui.screen.TaskUiState.Success
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import taskmanager.composeapp.generated.resources.Res
import taskmanager.composeapp.generated.resources.add_task
import taskmanager.composeapp.generated.resources.delete_task
import taskmanager.composeapp.generated.resources.no_task_availables

@Composable
fun TaskListScreen(
    state: TaskUiState,
    onEvent: (TaskEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(100.dp))
                }
            }

            is Empty -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.no_task_availables),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            is Error -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }

            is Success -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(all = 16.dp)
                ) {
                    items(
                        items = state.tasks,
                        key = { task -> task.id }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onRemoveClick = { onEvent(RemoveTask(task)) },
                            onTaskClick = { onNavigationEvent(NavigationEvent.NavigateToEdit(task.id)) }
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { onNavigationEvent(NavigateToAdd) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = stringResource(Res.string.add_task)
            )
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onRemoveClick: () -> Unit,
    onTaskClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .clickable { onTaskClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = task.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onRemoveClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = stringResource(Res.string.delete_task),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


@Preview()
@Composable
fun TaskListScreenLoadingPreview() {

    TaskListScreen(
        state = Loading,
        onEvent = {},
        onNavigationEvent = {}
    )

}

@Preview()
@Composable
fun TaskListScreenEmptyPreview() {
    TaskListScreen(
        state = Empty,
        onEvent = {},
        onNavigationEvent = {}
    )
}

@Preview()
@Composable
fun TaskListScreenWithTasksPreview() {
    TaskListScreen(
        state = Success(
            tasks = listOf(
                Task(id = 1, title = "Comprar leche", description = "Leche descremada"),
                Task(id = 2, title = "Hacer ejercicio", description = "30 minutos"),
                Task(id = 3, title = "Estudiar Compose", description = "Jetpack Compose")
            )
        ),
        onEvent = {},
        onNavigationEvent = {}
    )
}

@Preview()
@Composable
fun TaskListScreenErrorPreview() {
    TaskListScreen(
        state = Error("¡Ups! Algo salió mal"),
        onEvent = {},
        onNavigationEvent = {}
    )
}