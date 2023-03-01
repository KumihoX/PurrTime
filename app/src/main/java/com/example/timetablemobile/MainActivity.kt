package com.example.timetablemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.timetablemobile.navigation.Navigation
import com.example.timetablemobile.ui.presentation.mainscreen.MainScreen
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreen
import com.example.timetablemobile.ui.theme.TimetableMobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            TimetableMobileTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}
