package com.example.skills53dic.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skills53dic.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, drawerState: DrawerState, nav: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(50.dp)
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterStart) // Align to start (left)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "App Menu"
            )
        }
        Image(
            painter = painterResource(id = R.drawable.horizontal_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    nav.navigate("home")
                }
        )
    }
}