package com.example.timetablemobile.ui.presentation.mainscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //will be added later
    //private val getLessonsUseCase: GetLessonsUseCase
) : ViewModel() {

    private val _state: MutableLiveData<MainState> = MutableLiveData(MainState.Initial)
    val state: LiveData<MainState> = _state

    private val _helpDialogIsOpen = mutableStateOf(false)
    var helpDialogIsOpen: State<Boolean> = _helpDialogIsOpen

    init {
        //getLessons()
    }

    private fun getLessons(id: String, startDate: Date, endDate: Date) {
        viewModelScope.launch {

            _state.value = MainState.Loading

            try {
                //will be added later
                //val lessons = getLessonsUseCase()
                //_state.value = MainState.Content(lessons)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = MainState.Error(ex.message ?: "An unexpected error occurred")
            }
        }
    }

    fun openDialog() {
        _helpDialogIsOpen.value = true
    }

    fun closeDialog() {
        _helpDialogIsOpen.value = false
    }
}