package com.example.timetablemobile.ui.presentation.searchscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.theme.Black
import com.example.timetablemobile.ui.theme.MainGreen
import com.example.timetablemobile.ui.theme.SearchFieldBackground

@Composable
fun SearchListItem(
    itemName: String,
    //onItemClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
                    .padding(8.dp, 16.dp)
                    .wrapContentSize(),
                style = MaterialTheme.typography.body1,
                color = Black
            )

            Icon(
                painterResource(R.drawable.arrow_forward),
                contentDescription = null,
                Modifier.size(20.dp),
                tint = MainGreen
            )
        }

        Divider(
            Modifier.fillMaxWidth(),
            color = SearchFieldBackground,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchListItemPreview() {
    SearchListItem("972103")
}