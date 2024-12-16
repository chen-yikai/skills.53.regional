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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.components.BlackText
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.components.CustomTextButton
import com.example.skills53dic.components.Input
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

    SafeColumn {
        Column() {
            Column {
                BlackText("聯絡我們", 30.sp, FontWeight.Bold)
                Sh(20.dp)
                Input(title, "標題")
                Input(name, "姓名")
                Input(phone, "電話")
                Input(email, "電子郵件")
                Input(content, "內容", singleLine = false)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                CustomTextButton("重填") {
                    nav.navigate("contact")
                }
                Sw(5.dp)
                CustomButton("送出") {
                    if (title.value.trim().isEmpty() || name.value.trim()
                            .isEmpty() || phone.value.trim().isEmpty() || email.value.trim()
                            .isEmpty() || content.value.trim().isEmpty()
                    ) {
                        toast("請輸入完整資訊", context)
                    } else if (title.value.length >= 30) {
                        toast("標題不可超過30字元", context)
                    } else if (name.value.length >= 15) {
                        toast("姓名不可超過15字", context)
                    } else if (!Regex("^09\\d{8}").matches(phone.value)) {
                        toast("請輸入正確的電話號碼", context)
                    } else if (!Regex("^[\\w.]*@[a-zA-Z]+.[a-zA-Z]*").matches(
                            email.value
                        )
                    ) {
                        toast("請輸入正確的電子郵件", context)
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