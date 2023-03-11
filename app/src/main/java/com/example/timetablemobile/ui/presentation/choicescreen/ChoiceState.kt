package com.example.timetablemobile.ui.presentation.choicescreen

sealed interface ChoiceState {

    object Initial : ChoiceState

    object Loading : ChoiceState
}