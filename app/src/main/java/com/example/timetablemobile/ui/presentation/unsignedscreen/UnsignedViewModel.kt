package com.example.timetablemobile.ui.presentation.unsignedscreen

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsignedViewModel @Inject constructor(): ViewModel() {

    fun navigateToSearch(
        navController: NavController,
        userChoice: String
    ) {
        //надо что-то придумать со стрингами...
        when(userChoice) {
            "Аудитории" -> {
                navController.navigate(
                    Screen.SearchScreen.passUserChoice(
                        choiceHeader = "Аудитории",
                        placeholder = "Номер аудитории"
                    )
                )
                {
                    popUpTo(Screen.UnsignedScreen.route) { inclusive = true }
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
                    popUpTo(Screen.UnsignedScreen.route) { inclusive = true }
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
                    popUpTo(Screen.UnsignedScreen.route) { inclusive = true }
                }
            }
        }
    }
}