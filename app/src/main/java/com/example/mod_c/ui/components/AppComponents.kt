package com.example.mod_c.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PinField(){
    val pin = remember { mutableStateListOf("","","","") }
    val focusRequesters = List(4) { FocusRequester() }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .focusRequester(focusRequesters[index])
            ){
                TextField(
                    value = pin[index],
                    onValueChange = { value->

                            pin[index] = value.takeLast(1)

                            if (pin[index].isNotEmpty() && index < 3){
                                focusRequesters[index+1].requestFocus()
                            }
                             else if(pin[index].isEmpty() && index>0){
                                focusRequesters[index-1].requestFocus()
                            }

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPinField(){
    PinField()

}