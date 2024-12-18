package com.example.skills53dic.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.components.BlackText
import com.example.skills53dic.components.ColorGreen
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.components.CustomTextButton
import com.example.skills53dic.components.ContactInput
import com.example.skills53dic.components.SafeColumn
import com.example.skills53dic.components.Sh
import com.example.skills53dic.components.Sw
import kotlin.text.Regex

@Preview(showBackground = true)
@Composable
fun Contact(nav: NavController = rememberNavController()) {
    val context = LocalContext.current
    var title = remember { mutableStateOf("") }
    var name = remember { mutableStateOf("") }
    var phone = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var content = remember { mutableStateOf("") }
    var phoneError = if (phone.value.isEmpty()) {
        ""
    } else if (!Regex("^09\\d{8}").matches(phone.value)) {
        "格式錯誤"
    } else {
        ""
    }
    var emailError = if (email.value.isEmpty()) {
        ""
    } else if (!Regex("^[A-Za-z0-9+_.-]+@[a-zA-Z].[A-zA-z]\$").matches(email.value)) {
        "格式錯誤"
    } else {
        ""
    }

    SafeColumn {
        Column() {
            Column {
                BlackText("聯絡我們", 30.sp, FontWeight.Bold)
                Sh(20.dp)
                ContactInput(
                    title,
                    "標題",
                    errorMessage = if (title.value.length >= 30) "" else ""
                ) {
                    if (it.length <= 30) {
                        title.value = it
                    }
                }
                ContactInput(
                    name, "姓名", errorMessage = if (name.value.length >= 15) "" else ""
                ) {
                    if (it.length <= 15) {
                        name.value = it
                    }
                }
                ContactInput(
                    phone,
                    "電話",
                    errorMessage = phoneError
                ) {
                    phone.value = it
                }
                ContactInput(email, "電子郵件", errorMessage = emailError) {
                    if (it.length <= 30) {
                        email.value = it
                    }
                }
                ContactInput(content, "內容", singleLine = false) {
                    content.value = it
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                CustomTextButton("重填") {
                    nav.navigate("contact")
                }
                Sw(5.dp)
                CustomButton("送出") {
                    if (title.value.isBlank() || name.value.isBlank() || phone.value.isBlank() || email.value.isBlank() || content.value.isBlank()
                    ) {
                        toast("請輸入完整資訊", context)
                    } else {
                        toast("送出成功", context)
                        nav.navigate("contact")
                    }
                }
            }
        }
    }
}

fun toast(message: String = "Hello", context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}