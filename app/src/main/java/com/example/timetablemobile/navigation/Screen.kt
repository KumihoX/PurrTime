package com.example.timetablemobile.navigation

import com.example.timetablemobile.data.remote.dto.TeacherDto
import com.example.timetablemobile.domain.model.LessonTypeEnum

const val LESSON_NAME = "lesson_name"
const val LESSON_TYPE = "lesson_type"
const val LESSON_TIME = "lesson_time"
const val LESSON_TEACHER = "lesson_teacher"
const val LESSON_TEACHER_ID = "lesson_teacher_id"
const val LESSON_CLASSROOM = "lesson_classroom"
const val LESSON_CLASSROOM_ID = "lesson_classroom_id"
const val LESSON_GROUPS = "lesson_groups"

const val SCHEDULE_TYPE = "schedule_type"
const val DATA_ID = "data_id"
const val DATA = "data"

const val STUDENT_DATA = "student_data"
const val TEACHER_ID = "teacher_id"
const val TEACHER_NAME = "teacher_name"

const val USER_CHOICE_HEADER = "search_choice_header"
const val USER_CHOICE_PLACEHOLDER = "search_choice_placeholder"

sealed class Screen(val route: String) {
    object LoadingScreen : Screen("loading_screen")
    object SignInScreen : Screen("sign_in_screen")
    object UnsignedScreen : Screen("unsigned_screen")
    object ChoiceScreen : Screen("choice_screen/{$STUDENT_DATA}/{$TEACHER_ID}/{$TEACHER_NAME}") {
        fun passScheduleInfo(
            studentData: String,
            teacherId: String,
            teacherName: String,
        ): String = "choice_screen/$studentData/$teacherId/$teacherName"
    }

    object MainScreen : Screen("main_screen/{$SCHEDULE_TYPE}/{$DATA_ID}?{$DATA}") {
        fun passScheduleInfo(
            type: String,
            dataId: String,
            data: String? = null
        ): String = "main_screen/$type/$dataId?$data"
    }

    object SearchScreen : Screen("search_screen/{$USER_CHOICE_HEADER}/{$USER_CHOICE_PLACEHOLDER}") {
        fun passUserChoice(
            choiceHeader: String,
            placeholder: String
        ): String = "search_screen/$choiceHeader/$placeholder"
    }

    object LessonDetailScreen : Screen(
        "lesson_detail_screen/{$SCHEDULE_TYPE}/{$DATA_ID}?{$DATA}/" +
                "{$LESSON_NAME}/{$LESSON_TYPE}/{$LESSON_TIME}/" +
                "{$LESSON_TEACHER}/{$LESSON_TEACHER_ID}/{$LESSON_CLASSROOM}/" +
                "{$LESSON_CLASSROOM_ID}/{$LESSON_GROUPS}"
    ) {
        fun passLessonInfo(
            typeSchedule: String,
            dataIdSchedule: String,
            dataSchedule: String? = null,
            name: String,
            type: LessonTypeEnum,
            time: String,
            teacher: String,
            teacherId: String,
            classroom: String,
            classroomId: String,
            groups: String
        ): String = "lesson_detail_screen/$typeSchedule/$dataIdSchedule?$dataSchedule" +
                "/$name/$type/$time/$teacher/$teacherId/$classroom/$classroomId/$groups"
    }
}
