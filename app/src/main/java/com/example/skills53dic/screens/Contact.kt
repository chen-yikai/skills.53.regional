package com.example.skills53dic.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.components.BlackText
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.components.CustomTextButton
import com.example.skills53dic.components.Input
import com.example.skills53dic.components.SafeColumn
import com.example.skills53dic.components.Sh
import com.example.skills53dic.components.Sw

data class ContactData(
    var title: String = "",
    var name: String = "",
    var phone: String = "",
    var email: String = "",
    var content: String = ""
)

@Preview(showBackground = true)
@Composable
fun Contact(nav: NavController = rememberNavController(), contact: ContactViewModel = viewModel()) {

    SafeColumn {
        Column() {
            Column {
                BlackText("聯絡我們", 30.sp, FontWeight.Bold)
                Sh(20.dp)
                Input(contact.data.title, "標題") {
                    contact.data.title = it
                }
                Input(contact.data.name, "姓名") {
                    contact.data.name = it
                }
                Input(contact.data.phone, "電話") {
                    contact.data.phone = it
                }
                Input(contact.data.email, "Email") {
                    contact.data.email = it
                }
                Input(contact.data.content, "內容", singleLine = false) {
                    contact.data.content = it
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                CustomTextButton("重填") {
                    nav.navigate("contact")
                }
                Sw(5.dp)
                CustomButton("送出") {

                }
            }
        }
    }
}

class ContactViewModel : ViewModel() {
    var data by mutableStateOf(ContactData())
    var error by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun check() {

    }
}

