package com.example.timetablemobile.navigation

sealed class Screen(val route: String) {
    object SignInScreen: Screen("sign_in_screen")
    object UnsignedScreen: Screen("unsigned_screen")
}
