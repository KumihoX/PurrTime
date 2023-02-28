package com.example.timetablemobile.ui.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.MainGreen

@Composable
fun ErrorAlertDialog(
    message: String
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            buttons = {
                Column(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = message,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
                        color = MainGreen,
                        style = MaterialTheme.typography.h5
                    )

                    Image(
                        painter = painterResource(R.drawable.cat_error),
                        contentDescription = "Ошибка",
                        modifier = Modifier.align(CenterHorizontally)
                    )
                }
            },
            shape = RoundedCornerShape(8.dp)
        )
    }
}