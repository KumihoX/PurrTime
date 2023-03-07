package com.example.timetablemobile.ui.presentation.searchscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.ui.theme.Black
import com.example.timetablemobile.ui.theme.SearchFieldBackground
import com.example.timetablemobile.ui.theme.SearchFieldText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchField(
    text: String,
    placeholderValue: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body2,
        placeholder = {
            Text(
                text = placeholderValue,
                modifier = Modifier
                    //.padding(8.dp)
                    .wrapContentSize(),
                style = MaterialTheme.typography.body2,
                color = SearchFieldText
            )
        },
        trailingIcon = {
            if (text != "") {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = { onTextChange("") }
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = null,
                            Modifier.size(20.dp)
                        )
                    }
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Black,
            disabledTextColor = SearchFieldText,
            backgroundColor = SearchFieldBackground,
            cursorColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}