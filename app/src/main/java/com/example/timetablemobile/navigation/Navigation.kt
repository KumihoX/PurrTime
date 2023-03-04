package com.example.timetablemobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.timetablemobile.ui.presentation.lessondetailsscreen.LessonDetailScreen
import com.example.timetablemobile.ui.presentation.mainscreen.MainScreen
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreen
import com.example.timetablemobile.ui.presentation.unsignedscreen.UnsignedScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }

        composable(route = Screen.UnsignedScreen.route) {
            UnsignedScreen()
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.LessonDetailScreen.route,
            arguments = listOf(
                navArgument(LESSON_NAME) {
                    type = NavType.StringType
                },
                navArgument(LESSON_TYPE) {
                    type = NavType.StringType
                },
                navArgument(LESSON_TIME) {
                    type = NavType.StringType
                },
                navArgument(LESSON_TEACHER) {
                    type = NavType.StringType
                },
                navArgument(LESSON_CABINET) {
                    type = NavType.StringType
                },
                navArgument(LESSON_GROUPS) {
                    type = NavType.StringType
                },
            )) {
            it.arguments?.let { it1 -> LessonDetailScreen(it1, navController) }
        }
    }
}