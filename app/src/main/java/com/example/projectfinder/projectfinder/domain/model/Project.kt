package com.example.projectfinder.projectfinder.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
    val title: String,
    val projectType: String,
    val duration: String,
    val teamMembers: String,
    val description: String
)

class InvalidProjectException(message: String): Exception(message)
