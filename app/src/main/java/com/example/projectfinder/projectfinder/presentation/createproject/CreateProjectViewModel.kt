package com.example.projectfinder.projectfinder.presentation.createproject

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectfinder.projectfinder.domain.model.Project
import com.example.projectfinder.projectfinder.domain.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.projectfinder.projectfinder.domain.model.InvalidProjectException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    private val repository: ProjectRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(CreateProjectState())
    val uiState: StateFlow<CreateProjectState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var project by mutableStateOf<Project?>(null)
        private set



    init {


    }


    fun setDuration(duration: String) {
        _uiState.update { currentState ->
            currentState.copy(
                duration = duration,
            )
        }
    }

    fun setTitle(title: String) {
        _uiState.update { currentState ->
            currentState.copy(
                title = title,
            )
        }
    }

    fun setDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(
                description = description,
            )
        }
    }

    fun setTeamMembers(member: String) {
        _uiState.update { currentState ->
            currentState.copy(
                teamMembers = member,
            )
        }
    }

    fun setType(type: String) {
        _uiState.update { currentState ->
            currentState.copy(
                type = type,
            )
        }
    }

    fun saveProject() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pro = Project(
                    title = _uiState.value.title,
                    projectType = _uiState.value.type,
                    duration = _uiState.value.duration.toString(),
                    description = _uiState.value.description,
                    teamMembers = _uiState.value.teamMembers,

                    )
                repository.saveProject(pro)

                Log.d("test", "${_uiState.value.title} ${_uiState.value.type} ${_uiState.value.duration} ${_uiState.value.description} ${_uiState.value.teamMembers} +$pro")
                _eventFlow.emit(UiEvent.SaveProject)
            } catch (e: InvalidProjectException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save Project"
                    )
                )
            }
        }
    }



    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveProject: UiEvent()
    }

}