package com.example.timetablemobile.ui.presentation.loadingscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.ui.theme.MainGreen

@Composable
fun LoadingScreen(
    viewModel: LoadingScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by remember { viewModel.state }

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize())
    {
        when (state) {

            LoadingState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MainGreen
                )
                viewModel.checkAuthorization(context, navController)
            }
        }
    }
}