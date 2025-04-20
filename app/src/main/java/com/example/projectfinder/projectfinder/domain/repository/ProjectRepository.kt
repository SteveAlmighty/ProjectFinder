package com.example.projectfinder.projectfinder.domain.repository

import com.example.projectfinder.projectfinder.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getProjects(): Flow<List<Project>>

    suspend fun getProjectById(id: Int): Project?

    suspend fun saveProject(project: Project)

    suspend fun deleteProject(project: Project)
}