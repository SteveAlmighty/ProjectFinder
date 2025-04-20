package com.example.projectfinder.projectfinder.presentation.createproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.Navigation
import com.example.projectfinder.projectfinder.presentation.homescreen.Project

// Define the ProjectType enum
enum class ProjectType {
    Research,
    Development,
    Design,
    Marketing,
    Other
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(
    onCreateProject: (Project) -> Unit,
    viewModel: CreateProjectViewModel = hiltViewModel(),
    navigation: Navigation
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var projectType by remember { mutableStateOf(ProjectType.Other) }
    var members by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create New Project", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.setTitle(it) },
            label = { Text("Project Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.description,
            onValueChange = {viewModel.setDescription(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Project Type Dropdown
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                readOnly = true,
                value = projectType.name,
                onValueChange = {viewModel.setType(it)},
                label = { Text("Project Type") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ProjectType.values().forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name) },
                        onClick = {
                            projectType = type
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.teamMembers,
            onValueChange = { viewModel.setTeamMembers(it)},
            label = { Text("Number of Members") },
            modifier = Modifier.fillMaxWidth(),
            // Consider adding keyboard options for numeric input
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Validate input and create the project
                val numMembers = members.toIntOrNull()
                if (title.text.isNotBlank() && description.text.isNotBlank() && numMembers != null) {
                    val newProject = Project(
                        title = title.text,
                        description = description.text,
                        type = projectType.name,
                        members = numMembers
                    )
                    onCreateProject(newProject)
                } else {
                    // Handle validation errors (e.g., display a message)
                }
            }
        ) {
            Text("Create Project")
        }
    }
}

// Update the Project data class to make fields mutable:


/*@Preview(showBackground = true)
@Composable
fun CreateProjectScreenPreview() {
    CreateProjectScreen(onCreateProject = { /* Handle project creation in preview */ })
} */
