package com.example.timetablemobile.ui.presentation.signinscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.timetablemobile.R
import com.example.timetablemobile.navigation.Screen
import com.example.timetablemobile.ui.presentation.common.ErrorAlertDialog
import com.example.timetablemobile.ui.theme.MainGreen
import com.example.timetablemobile.ui.theme.White

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by remember { viewModel.state }

    Box(modifier = Modifier.fillMaxSize())
    {
        when (state) {
            SignInScreenState.Initial -> {
                SignInScreenUI(viewModel = viewModel, navController = navController)
            }

            SignInScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MainGreen
                )
            }

            is SignInScreenState.Content -> {
                SignInScreenUI(viewModel = viewModel, navController = navController)
            }

            is SignInScreenState.Error -> {
                SignInScreenUI(viewModel = viewModel, navController = navController)
                ErrorAlertDialog(message = (state as SignInScreenState.Error).error)
            }
        }
    }
}

@Composable
fun SignInScreenUI(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Logo()
        LoginField(viewModel = viewModel)
        PasswordField(viewModel = viewModel)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        LogIn(viewModel = viewModel, navController = navController)
        WithoutAuth(navController = navController)
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.cat_love),
        contentDescription = "Лого",
        modifier = Modifier.padding(0.dp, 64.dp, 0.dp, 32.dp)
    )
}

@Composable
fun LoginField(viewModel: SignInViewModel) {
    val login: String by remember { viewModel.login }

    OutlinedTextField(
        value = login,
        onValueChange = { viewModel.onLoginChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        placeholder = {
            Text(
                stringResource(R.string.login),
                style = MaterialTheme.typography.body2,
                color = MainGreen
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MainGreen,
            placeholderColor = MainGreen,
            cursorColor = MainGreen,
            textColor = MainGreen,
            focusedBorderColor = MainGreen
        )
    )
}

@Composable
fun PasswordField(viewModel: SignInViewModel) {
    val password: String by remember { viewModel.password }

    OutlinedTextField(
        value = password,
        onValueChange = { viewModel.onPasswordChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        placeholder = {
            Text(
                stringResource(R.string.password),
                style = MaterialTheme.typography.body2,
                color = MainGreen
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MainGreen,
            placeholderColor = MainGreen,
            cursorColor = MainGreen,
            textColor = MainGreen,
            focusedBorderColor = MainGreen
        )
    )
}

@Composable
fun LogIn(viewModel: SignInViewModel, navController: NavController) {
    val context = LocalContext.current
    val buttonState: Boolean by remember { viewModel.fieldsState }
    Button(
        onClick = {
            viewModel.login(navController, context)
        },
        enabled = buttonState,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MainGreen,
            contentColor = White,
            disabledBackgroundColor = White,
            disabledContentColor = MainGreen
        ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, MainGreen),
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.sign_in),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            fontSize = 16.sp
        )
    }
}

@Composable
fun WithoutAuth(navController: NavController) {
    TextButton(
        onClick = {
            navController.navigate(Screen.UnsignedScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 16.dp)

    ) {
        Text(
            stringResource(R.string.without_auth),
            color = MainGreen,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            fontSize = 16.sp
        )
    }
}