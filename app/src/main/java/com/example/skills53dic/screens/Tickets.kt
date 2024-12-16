package com.example.skills53dic.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skills53dic.R
import com.example.skills53dic.components.ColorBlue

@Preview(showBackground = true)
@Composable
fun Tickets() {
    val scrollState = rememberScrollState()
    Scaffold(topBar = {}, floatingActionButton = {
        IconButton(modifier = Modifier
            .padding(5.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(Color.White), onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.add),
                tint = ColorBlue,
                contentDescription = "Add new tickets",
                modifier = Modifier.size(30.dp)
            )
        }
    }) { innerpadding ->
        Column(modifier = Modifier
            .verticalScroll(scrollState)
            .padding(innerpadding)) {

        }
    }
}