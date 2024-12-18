package com.example.skills53dic.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BuyTickets() {
    val navController = rememberNavController()
    Scaffold(topBar = {

    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavHost(navController = navController, startDestination = "ticket_type") {
                composable("ticket_type") {
                    TicketType()
                }
                composable("ticket_detail") {
                    TicketDetail()
                }
            }

        }
    }
}

@Composable
fun TicketType() {

}

@Composable
fun TicketDetail() {

}

@Composable
fun BuyTicketsTopBar() {

}