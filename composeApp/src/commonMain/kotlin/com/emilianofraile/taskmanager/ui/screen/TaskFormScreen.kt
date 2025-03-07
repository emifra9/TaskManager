package com.emilianofraile.taskmanager.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.emilianofraile.taskmanager.data.Task
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import taskmanager.composeapp.generated.resources.Res
import taskmanager.composeapp.generated.resources.task_title
import taskmanager.composeapp.generated.resources.task_description
import taskmanager.composeapp.generated.resources.cancel
import taskmanager.composeapp.generated.resources.save
import taskmanager.composeapp.generated.resources.task_completed

@Composable
fun TaskFormScreen(
    state: TaskFormState,
    onEvent: (TaskEvent) -> Unit,
    onNavigationEvent: (NavigationEvent) -> Unit
) {
    var title by remember { mutableStateOf(state.title) }
    var description by remember { mutableStateOf(state.description) }
    var isCompleted by remember { mutableStateOf(state.isCompleted) }

    LaunchedEffect(state) {
        title = state.title
        description = state.description
        isCompleted = state.isCompleted
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (state.taskId > 0L && state.isCompleted) {
            Text(
                text = stringResource(Res.string.task_completed),
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.LineThrough,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(stringResource(Res.string.task_title)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(Res.string.task_description)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { onNavigationEvent(NavigationEvent.NavigateBack) }) {
                Text(stringResource(Res.string.cancel))
            }
            
            Button(
                onClick = { 
                    if (state.taskId > 0L) {
                        onEvent(TaskEvent.EditTask(Task(id = state.taskId, title = title, description = description, isCompleted = state.isCompleted)))
                    } else {
                        onEvent(TaskEvent.SaveTask(title = title, description = description))
                    }
                    onNavigationEvent(NavigationEvent.NavigateBack)
                },
                enabled = title.isNotBlank()
            ) {
                Text(stringResource(Res.string.save))
            }
        }
    }
}

@Preview()
@Composable
fun TaskFormScreenPreview() {
    TaskFormScreen(
        state = TaskFormState(
            taskId = 1L,
            title = "Sample Task",
            description = "This is a sample task description",
            isCompleted = true
        ),
        onEvent = {},
        onNavigationEvent = {}
    )
}