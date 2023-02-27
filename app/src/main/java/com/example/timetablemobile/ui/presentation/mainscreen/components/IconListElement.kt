package com.example.timetablemobile.ui.presentation.mainscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.ui.theme.Gray

@Composable
fun IconListElement(textValue: String, icon: Int) {
    Row(
        //Modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            Modifier.requiredSize(24.dp),
            tint = Gray
        )

        Text(
            text = textValue,
            modifier = Modifier
                .padding(start = 4.dp),
            style = MaterialTheme.typography.caption,
            color = Gray,
            textAlign = TextAlign.Start
        )
    }
}