package com.example.timetablemobile.navigation

const val LESSON_NAME = "lesson_name"
const val LESSON_TYPE = "lesson_type"
const val LESSON_TIME = "lesson_time"
const val LESSON_TEACHER = "lesson_teacher"
const val LESSON_CABINET = "lesson_cabinet"
const val LESSON_GROUPS = "lesson_groups"

sealed class Screen(val route: String) {
    object SignInScreen : Screen("sign_in_screen")
    object UnsignedScreen : Screen("unsigned_screen")
    object MainScreen : Screen("main_screen")
    object LessonDetailScreen: Screen("lesson_detail_screen/" +
            "{$LESSON_NAME}/{$LESSON_TYPE}/{$LESSON_TIME}/" +
            "{$LESSON_TEACHER}/{$LESSON_CABINET}/{$LESSON_GROUPS}"){
        fun passLessonInfo(
            name: String,
            type: String,
            time: String,
            teacher: String,
            cabinet: String,
            groups: String
        ): String = "lesson_detail_screen/$name/$type/$time/$teacher/$cabinet/$groups"
    }
}
