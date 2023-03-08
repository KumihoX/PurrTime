package com.example.timetablemobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.timetablemobile.navigation.Navigation
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreenState
import com.example.timetablemobile.ui.presentation.signinscreen.SignInScreenUI
import com.example.timetablemobile.ui.theme.MainGreen
import com.example.timetablemobile.ui.theme.TimetableMobileTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
