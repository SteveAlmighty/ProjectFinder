package com.example.projectfinder.projectfinder.data.repository


import com.example.projectfinder.projectfinder.data.local.ProjectDao
import com.example.projectfinder.projectfinder.domain.model.Project
import com.example.projectfinder.projectfinder.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDao: ProjectDao
): ProjectRepository {
    override fun getProjects(): Flow<List<Project>> {
        return projectDao.getProjects()

    }

    override suspend fun getProjectById(id: Int): Project? {
        return projectDao.getProjectById(id)

    }

    override suspend fun saveProject(project: Project) {
        return projectDao.upsert(project)

    }

    override suspend fun deleteProject(project: Project) {
        return projectDao.deleteProject(project)
    }


}