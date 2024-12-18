package com.example.skills53dic.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CustomButton(label: String = "Hello", padding: Dp = 0.dp, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        colors = buttonColors(ColorBlue),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(padding),
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTextButton(label: String = "Hello", onClick: () -> Unit = {}) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(label, color = ColorBlue)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorToast(message: String = "Hello", delay: Int = 2000) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Red)
            .padding(vertical = 5.dp, horizontal = 15.dp)
            .shadow(5.dp, CircleShape)
    ) {
        Text(message, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 20.sp)
    }
}

@Composable
fun ContactInput(
    value: MutableState<String>,
    label: String = "Hello",
    borderColor: Color = ColorGreen,
    errorMessage: String = "",
    fontSize: Int = 15,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit = {},
) {
    Column() {
        LightGrayText(label, 13.sp)
        Sh(3.dp)
        BasicTextField(
            value = value.value,
            onValueChange = onValueChange,
            textStyle = TextStyle.Default.copy(fontSize = fontSize.sp),
            singleLine = singleLine,
            maxLines = 10,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    if (errorMessage.isEmpty()) borderColor else Color.Red,
                    RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        )
        Text(errorMessage, color = Color.Red)
    }
}

@Composable
fun AddTicketInput(
    value: MutableState<String>,
    label: String = "Hello",
    borderColor: Color = ColorGreen,
    errorMessage: String = "",
    fontSize: Int = 15,
    singleLine: Boolean = true,
) {
    Column() {
        LightGrayText(label, 13.sp)
        Sh(3.dp)
        BasicTextField(
            value = value.value,
            onValueChange = { value.value = it },
            textStyle = TextStyle.Default.copy(fontSize = fontSize.sp),
            singleLine = singleLine,
            maxLines = 10,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    if (errorMessage.isEmpty()) borderColor else Color.Red,
                    RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        )
        Text(errorMessage, color = Color.Red)
    }
}
