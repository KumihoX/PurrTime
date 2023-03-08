package com.example.timetablemobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.timetablemobile.ui.presentation.lessondetailsscreen.LessonDetailScreen
import com.example.timetablemobile.ui.presentation.loadingscreen.LoadingScreen
import com.example.timetablemobile.ui.presentation.mainscreen.MainScreen
import com.example.timetablemobile.ui.presentation.searchscreen.SearchScreen
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreen
import com.example.timetablemobile.ui.presentation.unsignedscreen.UnsignedScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.LoadingScreen.route) {
        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navController = navController)
        }

        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }

        composable(route = Screen.UnsignedScreen.route) {
            UnsignedScreen(navController = navController)
        }

        composable(
            route = Screen.MainScreen.route,
            arguments = listOf(
                navArgument(SCHEDULE_TYPE) {
                    type = NavType.StringType
                },
                navArgument(TYPE_DATA) {
                    type = NavType.StringType
                },
            )
        ) {
            it.arguments?.let { it1 -> MainScreen(it1, navController) }
        }

        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
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
                navArgument(LESSON_CLASSROOM) {
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