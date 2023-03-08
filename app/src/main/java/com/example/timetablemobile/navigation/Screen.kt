package com.example.timetablemobile.navigation

const val LESSON_NAME = "lesson_name"
const val LESSON_TYPE = "lesson_type"
const val LESSON_TIME = "lesson_time"
const val LESSON_TEACHER = "lesson_teacher"
const val LESSON_CLASSROOM = "lesson_classroom"
const val LESSON_GROUPS = "lesson_groups"

const val SCHEDULE_TYPE = "schedule_type"
const val TYPE_DATA = "type_data"

const val STUDENT_DATA = "student_data"
const val TEACHER_DATA = "teacher_data"

const val USER_CHOICE_HEADER = "search_choice_header"
const val USER_CHOICE_PLACEHOLDER = "search_choice_placeholder"

sealed class Screen(val route: String) {
    object LoadingScreen : Screen("loading_screen")
    object SignInScreen : Screen("sign_in_screen")
    object UnsignedScreen : Screen("unsigned_screen")
    object ChoiceScreen : Screen("choice_screen/{$STUDENT_DATA}/{$TEACHER_DATA}") {
        fun passScheduleInfo(
            studentData: String,
            teacherData: String
        ): String = "choice_screen/$studentData/$teacherData"
    }

    object MainScreen : Screen("main_screen/{$SCHEDULE_TYPE}/{$TYPE_DATA}") {
        fun passScheduleInfo(
            type: String,
            data: String
        ): String = "main_screen/$type/$data"
    }

    object SearchScreen : Screen("search_screen/{$USER_CHOICE_HEADER}/{$USER_CHOICE_PLACEHOLDER}") {
        fun passUserChoice(
            choiceHeader: String,
            placeholder: String
        ): String = "search_screen/$choiceHeader/$placeholder"
    }

    object LessonDetailScreen : Screen(
        "lesson_detail_screen/" +
                "{$LESSON_NAME}/{$LESSON_TYPE}/{$LESSON_TIME}/" +
                "{$LESSON_TEACHER}/{$LESSON_CLASSROOM}/{$LESSON_GROUPS}"
    ) {
        fun passLessonInfo(
            name: String,
            type: String,
            time: String,
            teacher: String,
            classroom: String,
            groups: String
        ): String = "lesson_detail_screen/$name/$type/$time/$teacher/$classroom/$groups"
    }
}
