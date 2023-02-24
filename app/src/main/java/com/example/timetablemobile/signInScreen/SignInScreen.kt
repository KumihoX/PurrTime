package com.example.timetablemobile.signInScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

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
        LogIn(viewModel = viewModel)
        WithoutAuth(viewModel = viewModel)
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
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
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
fun LogIn(viewModel: SignInViewModel) {
    Button(
        onClick = { /*TODO*/ },
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DarkGreen,
            contentColor = White,
            disabledBackgroundColor = White,
            disabledContentColor = DarkGreen
        ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, DarkGreen),
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.sign_in),
            //fontFamily = IBM,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            fontSize = 16.sp
        )
    }
}

@Composable
fun WithoutAuth(viewModel: SignInViewModel) {
    TextButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 16.dp)

    ) {
        Text(
            stringResource(R.string.without_auth),
            color = DarkGreen,
            //fontFamily = IBM,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp,
            fontSize = 16.sp
        )
    }
}