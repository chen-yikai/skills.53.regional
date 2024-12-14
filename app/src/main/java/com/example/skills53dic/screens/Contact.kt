package com.example.skills53dic.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills53dic.components.BlackText
import com.example.skills53dic.components.Input
import com.example.skills53dic.components.Sh

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Contact() {
    var title = mutableStateOf<String>("")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        BlackText("聯絡我們", 20.sp, FontWeight.Bold)
        Sh(20.dp)
        Column {
            Input(title, "Title")

        }
    }
}