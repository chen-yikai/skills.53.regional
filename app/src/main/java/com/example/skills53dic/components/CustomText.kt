package com.example.skills53dic.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.lang.reflect.Modifier

val ColorLightGray = Color(0xFF707070)
val ColorBlue = Color(0xFF11617F)
val ColorGreen = Color(0xFF1AAB9F)
val ColorBlack = Color(0xFF333333)

@Composable
fun BlackText(
    text: String,
    size: TextUnit = 20.sp,
    weight: FontWeight = FontWeight.Normal,
) {
    Text(text, color = Color(0xFF333333), fontSize = size, fontWeight = weight)
}

@Composable
fun LightGrayText(text: String, size: TextUnit = 20.sp, weight: FontWeight = FontWeight.Normal) {
    Text(text, color = Color(0xFF707070), fontSize = size, fontWeight = weight)
}


@Composable
fun BlueText(text: String, size: TextUnit = 20.sp, weight: FontWeight = FontWeight.Normal) {
    Text(text, color = Color(0xFF11617F), fontSize = size, fontWeight = weight)
}

@Composable
fun GreenText(text: String, size: TextUnit = 20.sp, weight: FontWeight = FontWeight.Normal) {
    Text(text, color = Color(0xFF1AAB9F), fontSize = size, fontWeight = weight)
}