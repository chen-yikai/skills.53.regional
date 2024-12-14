package com.example.skills53dic.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Input(value: MutableState<String>, label: String) {
    BasicTextField(
        value = value.value,
        onValueChange = { value.value = it },
        textStyle = TextStyle.Default.copy(fontSize = 20.sp),
        decorationBox = { innerTextField ->
            Box() {
                if (value.value.isEmpty()) {
                    Text(label)
                }
                innerTextField()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, ColorGreen, RoundedCornerShape(10.dp))
    )
}