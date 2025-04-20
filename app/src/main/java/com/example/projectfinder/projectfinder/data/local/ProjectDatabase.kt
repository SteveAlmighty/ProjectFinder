package com.example.projectfinder.projectfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectfinder.projectfinder.domain.model.Project

@Database(
    entities = [Project::class],
    version = 1
)

abstract class ProjectDatabase: RoomDatabase() {

    abstract val projectDao: ProjectDao

    companion object {
        const val DB_NAME = "project.db"
    }
}