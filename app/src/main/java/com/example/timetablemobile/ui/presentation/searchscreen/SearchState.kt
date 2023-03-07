package com.example.timetablemobile.ui.presentation.searchscreen

sealed interface SearchState {

    object Initial : SearchState

    object Loading : SearchState

    data class Content(val requestResultsList: List<Any>) : SearchState

    data class Error(val error: String) : SearchState
}