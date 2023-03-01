package com.example.timetablemobile.ui.presentation.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timetablemobile.R
import com.example.timetablemobile.ui.presentation.mainscreen.MainViewModel
import com.example.timetablemobile.ui.theme.*

@Composable
fun ColorAlertDialog(
    viewModel: MainViewModel
) {
    AlertDialog(
        onDismissRequest = { viewModel.closeDialog() },
        modifier = Modifier
            //.padding(vertical = 44.dp)
            .wrapContentSize(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = White,
        buttons = {
            Box {
                ColorAlertDialogBody(viewModel)

                Image(
                    painter = painterResource(R.drawable.corner_cat),
                    contentDescription = null,
                    Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorAlertDialogBody(
    viewModel: MainViewModel
) {
    Column(
        Modifier
            .padding(16.dp, 12.dp)
            //.wrapContentSize()
            .wrapContentHeight()
            .width(260.dp)
    ) {
        Row(
            Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(R.string.what_do_the_colors_mean),
                modifier = Modifier
                    .wrapContentSize(),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start
            )

            CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                IconButton(
                    onClick = { viewModel.closeDialog() }
                ) {
                    Icon(
                        Icons.Outlined.Close,
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                }
            }
        }

        ColorDefinitionElement(color = LectionColor, text = stringResource(R.string.lection))
        ColorDefinitionElement(color = SeminarColor, text = stringResource(R.string.seminar))
        ColorDefinitionElement(color = PracticeColor, text = stringResource(R.string.practice))
        ColorDefinitionElement(color = LabColor, text = stringResource(R.string.lab))
        ColorDefinitionElement(color = IndividualLessonColor, text = stringResource(R.string.individual_lesson))
        ColorDefinitionElement(color = ContactWorkColor, text = stringResource(R.string.contact_work))
        ColorDefinitionElement(color = ControlWorkColor, text = stringResource(R.string.control_work))
        ColorDefinitionElement(color = ConsultationColor, text = stringResource(R.string.consultation))
        ColorDefinitionElement(color = BookingColor, text = stringResource(R.string.booking))
    }
}

@Composable
fun ColorDefinitionElement(color: Color, text: String) {
    Row(
        Modifier
            .padding(vertical = 8.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(0.dp, 8.dp, 0.dp, 8.dp))
                .background(color)
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = text,
            Modifier.wrapContentSize(),
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Start,
            maxLines = 2
        )
    }
}