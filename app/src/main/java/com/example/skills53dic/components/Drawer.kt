package com.example.skills53dic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(drawerState: DrawerState) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.7f)
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .padding(start = 20.dp)
    ) {
        Text("Drawer")
    }
}