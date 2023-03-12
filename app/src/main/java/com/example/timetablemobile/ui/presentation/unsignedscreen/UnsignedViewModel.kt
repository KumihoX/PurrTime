package com.example.timetablemobile.ui.presentation.unsignedscreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsignedViewModel @Inject constructor() : ViewModel() {

    fun navigateToSearch(
        navController: NavController,
        userChoice: String
    ) {
        when (userChoice) {
            "Аудитории" -> {
                navController.navigate(
                    Screen.SearchScreen.passUserChoice(
                        choiceHeader = "Аудитории",
                        placeholder = "Номер аудитории"
                    )
                )
                {
                    launchSingleTop = true
                }
            }
            "Группы" -> {
                navController.navigate(
                    Screen.SearchScreen.passUserChoice(
                        choiceHeader = "Группы",
                        placeholder = "Номер группы"
                    )
                )
                {
                    launchSingleTop = true
                }
            }
            "Преподавателя" -> {
                navController.navigate(
                    Screen.SearchScreen.passUserChoice(
                        choiceHeader = "Преподаватели",
                        placeholder = "Имя преподавателя"
                    )
                )
                {
                    launchSingleTop = true
                }
            }
        }
    }
}