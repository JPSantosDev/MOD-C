package com.example.mod_c.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PinField(){
    val pin = remember { mutableStateOf("") }

    repeat(4){ index->

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(48.dp)
        )

    }

}

@Preview
@Composable
fun PreviewPinField(){

}