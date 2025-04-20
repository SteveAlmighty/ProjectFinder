package com.example.projectfinder.projectfinder.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailsScreen(project: Project, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(text = project.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Type: ${project.type}", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Members: ${project.members}", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onBackClick,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Back to Home")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectDetailsScreenPreview() {
    val sampleProject = Project(
        title = "Sample Project",
        description = "This is a detailed description of the sample project. It includes all relevant information about the project's goals, objectives, and progress.",
        type = "Development",
        members = 4
    )
    ProjectDetailsScreen(project = sampleProject, onBackClick = { /* Handle back click in preview */ })
}