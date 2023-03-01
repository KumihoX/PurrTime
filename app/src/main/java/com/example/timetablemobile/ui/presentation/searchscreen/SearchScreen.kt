package com.example.timetablemobile.ui.presentation.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.Black
import com.example.timetablemobile.ui.theme.MainGreen
import com.example.timetablemobile.ui.theme.SearchFieldBackground
import com.example.timetablemobile.ui.theme.SearchFieldText

@Composable
fun SearchScreen() {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholderValue: String
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .wrapContentSize(),
        textStyle = MaterialTheme.typography.body2,
        placeholder = {
              Text(
                  text = placeholderValue,
                  modifier = Modifier
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
            cursorColor = Black
        )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItem(
    itemName: String,
    //onItemClick: () -> Unit
) {
    Column(
        Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { /*onItemClick()*/ },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = itemName,
                Modifier
                    .wrapContentSize()
                    .padding(8.dp, 4.dp, 0.dp, 4.dp),
                style = MaterialTheme.typography.body2,
                color = Black
            )

            Icon(
                painterResource(R.drawable.arrow_forward),
                contentDescription = null,
                Modifier.size(20.dp),
                tint = MainGreen
            )
        }

        Spacer(
            Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(SearchFieldBackground)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    ListItem("972103")
}