package com.example.timetablemobile.ui.presentation.loadingscreen

sealed interface LoadingState {
    object Initial : LoadingState

    object Loading : LoadingState
}