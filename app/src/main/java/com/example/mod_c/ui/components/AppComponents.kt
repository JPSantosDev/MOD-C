package com.example.mod_c.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PinField(){
    var pin by remember { mutableStateOf("") }


    BasicTextField(
        value = pin,
        onValueChange = {
            if (pin.length <=4 && pin.all { it.isDigit() }) pin=it
        },
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .border(1.dp,Color.Gray),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = pin.getOrNull(index)?.toString() ?: ""
                        )
                    }
                }
            }
        }
    )

}

@Preview
@Composable
fun PreviewPinField(){
    PinField()

}