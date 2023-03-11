package com.example.timetablemobile.ui.presentation.choicescreen

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.navigation.STUDENT_DATA
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.navigation.TEACHER_ID
import com.example.timetablemobile.navigation.TEACHER_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChoiceViewModel @Inject constructor() : ViewModel() {

    private var studentData = ""
    private var teacherId = ""
    private var teacherName = ""

    fun handOverData(
        info: Bundle
    ) {
        val studentInfo = info.getString(STUDENT_DATA).toString()
        val teacherInfoId = info.getString(TEACHER_ID).toString()
        val teacherInfoName = info.getString(TEACHER_NAME).toString()
        studentData = studentInfo
        teacherId = teacherInfoId
        teacherName = teacherInfoName
    }

    fun navigateToSearch(
        navController: NavController,
        userChoice: String
    ) {
        when (userChoice) {
            "Группы" -> {
                navController.navigate(
                    Screen.MainScreen.passScheduleInfo(
                        type = "STUDENT",
                        dataId = studentData
                    )
                ) {
                    popUpTo(Screen.ChoiceScreen.route) { inclusive = true }
                }
            }
            "Преподавателя" -> {
                navController.navigate(
                    Screen.MainScreen.passScheduleInfo(
                        type = "TEACHER",
                        dataId = teacherId,
                        data = teacherName
                    )
                ) {
                    popUpTo(Screen.ChoiceScreen.route) { inclusive = true }
                }
            }
        }
    }
}