package com.example.skills53dic.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skills53dic.components.ColorLightGray
import com.example.skills53dic.components.SafeColumn

@Preview(showBackground = true)
@Composable
fun AddTicket() {
    Scaffold(topBar = {}) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            SafeColumn {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(1.dp, ColorLightGray, RoundedCornerShape(10.dp))
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTicketTopBar(){
    Box(modifier = Modifier.fillMaxWidth()){

    }
}