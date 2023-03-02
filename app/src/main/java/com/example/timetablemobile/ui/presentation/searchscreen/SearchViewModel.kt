package com.example.timetablemobile.ui.presentation.searchscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    //private val getClassroomsUseCase: GetClassroomsListUseCase,
    //private val getGroupsUseCase: GetGroupsListUseCase,
    //private val getTeachersUseCase: GetTeachersListUseCase
) : ViewModel() {

    private val _state: MutableState<SearchState> = mutableStateOf(SearchState.Initial)
    var state: State<SearchState> = _state

    init {
        //val results = listOf(123, 123, 123, 124, 123, 123, 123, 124, 123, 123, 123, 124)
        //_state.value = SearchState.Content(results)
        /*savedStateHandle.get<String>(Constants.PARAM_UNSIGNED_CHOICE)?.let { choice ->
            when(choice) {
                R.string.classroom.toString() -> getClassrooms()
                R.string.group.toString() -> getGroups()
                R.string.teacher.toString() -> getTeachers()
                else -> _state.value = SearchState.Error("Что-то пошло не так")
            }
        }*/
    }

    private val _searchFieldText = mutableStateOf("")
    var searchFieldText: State<String> = _searchFieldText

    fun onSearchFieldChange(newValue: String) {
        _searchFieldText.value = newValue
    }

    private fun getClassrooms() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {
                //val classrooms = getClassroomsUseCase()
                //_state.value = SearchState.Content(classrooms)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    private fun getGroups() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {
                //val groups = getGroupsUseCase()
                //_state.value = SearchState.Content(groups)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }

    private fun getTeachers() {
        viewModelScope.launch {
            _state.value = SearchState.Loading

            try {
                //val teachers = getTeachersUseCase()
                //_state.value = SearchState.Content(teachers)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = SearchState.Error("Что-то пошло не так")
            }
        }
    }
}