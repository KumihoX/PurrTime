package com.example.timetablemobile.ui.presentation.searchscreen

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.navigation.USER_CHOICE_HEADER
import com.example.timetablemobile.navigation.USER_CHOICE_PLACEHOLDER
import com.example.timetablemobile.ui.presentation.common.ErrorAlertDialog
import com.example.timetablemobile.ui.presentation.searchscreen.components.SearchField
import com.example.timetablemobile.ui.presentation.searchscreen.components.SearchListItem
import com.example.timetablemobile.ui.theme.MainGreen

@Composable
fun SearchScreen(
    userChoice: Bundle,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val header = userChoice.getString(USER_CHOICE_HEADER).toString()
    val searchFieldPlaceholder = userChoice.getString(USER_CHOICE_PLACEHOLDER).toString()

    val state by remember { viewModel.state }

    val searchFieldText: String by remember { viewModel.searchFieldText }

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            Column(
                Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier
                        .padding(bottom = 20.dp)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.UnsignedScreen.route) {
                                popUpTo(Screen.SearchScreen.route) { inclusive = true }
                            }
                        },
                        Modifier
                            .requiredSize(24.dp)
                    ) {
                        Icon(
                            Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = MainGreen
                        )
                    }

                    Text(
                        text = header,
                        Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                SearchField(
                    text = searchFieldText,
                    placeholderValue = searchFieldPlaceholder
                ) { viewModel.onSearchFieldChange(it, (state as SearchState.Content).requestResultsList) }
            }
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {

                SearchState.Initial -> EmptySearchScreen()

                SearchState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center),
                        color = MainGreen
                    )
                }

                is SearchState.Content -> {

                    val searchResult: List<Any> by remember { viewModel.searchResult }

                    if (searchResult.isEmpty()) {
                        EmptySearchScreen()
                    } else {
                        LazyColumn(
                            Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxSize()
                        ) {
                            items(searchResult) { result ->
                                SearchListItem(
                                    itemName = result.toString()
                                ) //{  }
                            }
                        }
                    }
                }

                is SearchState.Error -> {
                    ErrorAlertDialog(message = (state as SearchState.Error).error)
                }
            }
        }
    }
}

@Composable
fun EmptySearchScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.nothing_here),
            Modifier
                .align(TopCenter),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

        Image(
            painterResource(R.drawable.cat_bottom_question),
            contentDescription = null,
            Modifier.align(BottomCenter)
        )
    }
}