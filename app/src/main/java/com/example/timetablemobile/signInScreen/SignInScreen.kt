package com.example.timetablemobile.signInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.DarkGreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun SignInScreen () {
    val viewModel: SignInViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Logo()
        LoginField(viewModel)
        PasswordField(viewModel)
    }

    /*Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Logo()
        LoginField(viewModel)
        PasswordField(viewModel)
    }*/
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
        onValueChange = {viewModel.onLoginChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        placeholder = {
            Text(
                stringResource(R.string.login),
                style = MaterialTheme.typography.body2,
                color = DarkGreen
            )
        },
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = DarkGreen,
            placeholderColor = DarkGreen,
            cursorColor = DarkGreen,
            textColor = DarkGreen,
            focusedBorderColor = DarkGreen
        )
    )
}

@Composable
fun PasswordField(viewModel: SignInViewModel) {
    val password: String by remember { viewModel.password }

    OutlinedTextField(
        value = password,
        onValueChange = {viewModel.onPasswordChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        placeholder = {
            Text(
                stringResource(R.string.password),
                style = MaterialTheme.typography.body2,
                color = DarkGreen
            )
        },
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = DarkGreen,
            placeholderColor = DarkGreen,
            cursorColor = DarkGreen,
            textColor = DarkGreen,
            focusedBorderColor = DarkGreen
        )
    )
}