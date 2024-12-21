package com.example.skills53dic.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SignUp() {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var passwordConfirm = remember { mutableStateOf("") }


}

@Composable
fun SignIn() {

}