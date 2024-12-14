package com.example.skills53dic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.components.DrawerContent
import com.example.skills53dic.components.TopBar
import com.example.skills53dic.screens.AboutInfo
import com.example.skills53dic.screens.AboutOperator
import com.example.skills53dic.screens.Contact
import com.example.skills53dic.screens.Floor3d
import com.example.skills53dic.screens.Home
import com.example.skills53dic.screens.MediaCenterDetail
import com.example.skills53dic.screens.PublicArt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen()
        }
    }
}

@Composable
fun NavHoster(
    mediaCenterDetailViewModel: MediaCenterDetailViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(drawerState, navController) },
        gesturesEnabled = false
    ) {
        Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars), topBar = {
            if (currentRoute != "media_center_detail") {
                TopBar(scope, drawerState, navController)
            }
        }) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        Home(navController, mediaCenterDetailViewModel)
                    }
                    composable("media_center_detail") {
                        MediaCenterDetail(navController, mediaCenterDetailViewModel)
                    }
                    composable("about_operator") {
                        AboutOperator()
                    }
                    composable("about_info") {
                        AboutInfo()
                    }
                    composable("floor_3d") {
                        Floor3d()
                    }
                    composable("contact") {
                        Contact()
                    }
                    composable("public_art") {
                        PublicArt()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen() {
    var showSplashScreen by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(0)
        showSplashScreen = false
    }
    if (showSplashScreen) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "Splash Screen",
                modifier = Modifier.size(200.dp)
            )
        }
    } else {
        NavHoster()
    }
}