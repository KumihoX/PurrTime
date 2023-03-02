package com.example.timetablemobile.ui.presentation.searchscreen

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
import com.example.timetablemobile.ui.presentation.common.ErrorAlertDialog
import com.example.timetablemobile.ui.presentation.searchscreen.components.SearchField
import com.example.timetablemobile.ui.presentation.searchscreen.components.SearchListItem
import com.example.timetablemobile.ui.presentation.signinscreen.SignInState
import com.example.timetablemobile.ui.theme.MainGreen

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
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
                        onClick = { /*TODO*/ },
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
                        text = "optionName",
                        Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                SearchField(
                    text = searchFieldText,
                    placeholderValue = "temp"
                ) { viewModel.onSearchFieldChange(it) }
            }
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                //это состояние будет показываться, если по введенному запросу ничего не нашлось
                SearchState.Initial -> {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            stringResource(R.string.nothing_here),
                            Modifier
                                .align(Center),
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

                SearchState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Center),
                        color = MainGreen
                    )
                }

                is SearchState.Content -> {
                    val results = (state as SearchState.Content).requestResultsList

                    LazyColumn(
                        Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                    ) {
                        items(results) { result ->
                            SearchListItem(
                                itemName = result.toString()
                            ) //{  }
                        }
                    }
                }

                is SearchState.Error -> {
                    ErrorAlertDialog(message = (state as SignInState.Error).error)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}