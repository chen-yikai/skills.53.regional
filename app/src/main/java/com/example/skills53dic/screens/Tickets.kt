package com.example.skills53dic.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.R
import com.example.skills53dic.components.ColorBlue
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.db.TicketsSchema
import com.example.skills53dic.db.TicketsViewModel

@Composable
fun Tickets(nav: NavController = rememberNavController(), db: TicketsViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current;
    Scaffold(floatingActionButton = {
        IconButton(modifier = Modifier
            .padding(5.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(Color.White), onClick = { nav.navigate("add_ticket") }) {
            Icon(
                painter = painterResource(R.drawable.add),
                tint = ColorBlue,
                contentDescription = "Add new tickets",
                modifier = Modifier.size(30.dp)
            )
        }
    }) { innerpadding ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerpadding)
        ) {
//            data.tickets.forEach {
//                Box(modifier = Modifier.padding(10.dp)) {
//                    Text(it.title)
//                }
//            }
        }
    }
}