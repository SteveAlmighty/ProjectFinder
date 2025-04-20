package com.example.projectfinder.projectfinder.presentation.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ... (Project data class remains the same as in the previous response)

data class Project(
    val title: String,
    val description: String,
    val type: String,
    val members: Int
)


@Composable
fun ProjectList(projects: List<Project>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(projects) { project ->
            ProjectCard(project = project) {
                // Handle project card click (e.g., navigate to project details)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader() {
    TopAppBar(
        title = { Text("Project Finder") }, // Replace with your app title or logo
        actions = {
            IconButton(onClick = { /* Handle user management action */ }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "User Management")
            }
        }
    )
}

@Composable
fun HomeScreen(projects: List<Project>, onAddProjectClick: () -> Unit) {
    Scaffold(
        topBar = { HomeHeader()
        },
        bottomBar = { HomeBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProjectClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Project")
            }
        },
        content = { paddingValues ->
            ProjectList(projects = projects, modifier = Modifier.padding(paddingValues))
        }
    )
}

// ... (HomeHeader and ProjectList composables remain the same)

@Composable
fun ProjectCard(project: Project, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = project.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = project.type, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Filled.Person, contentDescription = "Members") // Updated icon for members
                Text(text = project.members.toString(), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun HomeBottomNavigation() {
    NavigationBar {
        // Define your navigation items here.  This is a placeholder.
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true, // Assuming this is the "Home" screen
            onClick = { /* Handle click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = false,
            onClick = { /* Handle click */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = { /* Handle click */ }
        )
    }
}

// ... (sampleProjects and HomeScreenPreview remain the same, but update the preview to include the FAB click handler)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val sampleProjects = listOf(
        Project("Project Alpha", "A new initiative", "Research", 5),
        Project("Project Beta", "Improving existing systems", "Development", 3),
        // Add more sample projects
    )

    HomeScreen(projects = sampleProjects, onAddProjectClick = { /* Handle FAB click in preview */ })
}