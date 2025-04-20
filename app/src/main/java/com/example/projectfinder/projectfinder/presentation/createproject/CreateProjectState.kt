package com.example.projectfinder.projectfinder.presentation.createproject

data class CreateProjectState (
    val title: String = "",
    val description: String = "",
    val type: String = "",
    val duration: String = "",
    val teamMembers: String = ""
)