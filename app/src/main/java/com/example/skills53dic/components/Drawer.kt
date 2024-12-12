package com.example.skills53dic.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.gson.Gson

data class SubNavLinksList(
    val title: String,
    val route: String,
)

data class NavLinksList(
    val title: String,
    val route: String,
    val sub: List<NavLinksList> = emptyList()
)

@Composable
fun DrawerContent(drawerState: DrawerState) {
    val context = LocalContext.current
    val navLinksJson = context.assets.open("nav_links.json")
        .bufferedReader()
        .use { it.readText() }
    val navLinks = Gson().fromJson(navLinksJson, Array<NavLinksList>::class.java).toList()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(LocalConfiguration.current.screenWidthDp.dp * 0.7f)
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .padding(start = 20.dp)
    ) {
        navLinks.forEach { navlinks ->
            Column {
                TextButton(onClick = {}) {
                    Text("${navlinks.title}")
                }
                if (navlinks.sub.isNotEmpty()) {
                    Column {
                        navlinks.sub.forEach { subitem ->
                            Text("${subitem.title}")
                        }
                    }
                }
            }
        }
    }
}