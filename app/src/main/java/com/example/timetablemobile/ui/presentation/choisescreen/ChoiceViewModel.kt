package com.example.timetablemobile.ui.presentation.choisescreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChoiceViewModel @Inject constructor(): ViewModel() {

    private var studentData = ""
    private var teacherData = ""

    fun handOverData(
        studentInfo: String,
        teacherInfo: String
    ) {
        studentData = studentInfo
        teacherData = teacherInfo
    }

    fun navigateToSearch(
        navController: NavController,
        userChoice: String
    ) {
        when(userChoice) {
            "Группы" -> {
                navController.navigate(
                    Screen.MainScreen.passScheduleInfo(
                        type = "STUDENT",
                        data = studentData
                    )
                )
            }
            "Преподавателя" -> {
                navController.navigate(
                    Screen.MainScreen.passScheduleInfo(
                        type = "TEACHER",
                        data = teacherData
                    )
                )
            }
        }
    }
}