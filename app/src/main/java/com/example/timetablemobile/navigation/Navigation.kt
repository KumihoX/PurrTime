package com.example.timetablemobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    }
}