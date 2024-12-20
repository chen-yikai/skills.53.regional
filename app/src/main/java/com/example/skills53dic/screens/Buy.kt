package com.example.skills53dic.screens

import android.hardware.lights.Light
import android.widget.GridView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.skills53dic.BuyTicketViewModel
import com.example.skills53dic.R
import com.example.skills53dic.components.BlueText
import com.example.skills53dic.components.ColorBlue
import com.example.skills53dic.components.ColorLightGray
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.components.LightGrayText
import com.example.skills53dic.components.SafeColumn
import com.example.skills53dic.components.Sh
import com.example.skills53dic.components.Sw
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

@Composable
fun BuyTicket(RootNav: NavController = rememberNavController()) {
    val navController = rememberNavController()
    val viewModel = BuyTicketViewModel()
    Scaffold(topBar = {
        BuyTicketsTopBar(RootNav)
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavHost(navController = navController, startDestination = "ticket_type") {
                composable("ticket_type") {
                    TicketType(navController, viewModel)
                }
                composable("ticket_detail") {
                    TicketDetail()
                }
            }

        }
    }
}

data class TicketTypeSchema(
    val ticketType: String, val price: Int
)

@Preview(showBackground = true)
@Composable
fun TicketType(
    nav: NavController = rememberNavController(), viewModel: BuyTicketViewModel = viewModel()
) {
    val context = LocalContext.current
    val gson = Gson()
    val dataJson: String = context.assets.open("ticket_type.json").use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readText()
        }
    }
    val data = gson.fromJson(dataJson, Array<TicketTypeSchema>::class.java)

    SafeColumn {
        Column(
            modifier = Modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .border(1.dp, ColorLightGray, RoundedCornerShape(10.dp))
                .background(
                    Color.White
                )
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "票種", color = ColorLightGray, modifier = Modifier.weight(1f)
                )
                Text(
                    "價格", color = ColorLightGray, modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        "數量", color = ColorLightGray, modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            data.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        item.ticketType,
                        color = ColorBlue,
                        modifier = Modifier
                            .weight(1f)
                            .width(50.dp)
                    )
                    Text(
                        "NT " + item.price.toString(),
                        color = ColorBlue,
                        modifier = Modifier
                            .weight(1f)
                            .width(50.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.width(120.dp)
                    ) {
                        IconButton(modifier = Modifier.size(40.dp, 40.dp), onClick = {
                            viewModel.setTicketTypeData(
                                index, (viewModel.ticketTypeData.value[index] - 1).toString()
                            )
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.minus),
                                contentDescription = "minus"
                            )
                        }
                        Box(
                            modifier = Modifier.size(40.dp, 30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicTextField(
                                value = viewModel.ticketTypeData.value[index].toString(),
                                onValueChange = {
                                    viewModel.setTicketTypeData(index, it)
                                },
                                modifier = Modifier.align(Alignment.Center),
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                            )
                            HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter))
                        }
                        IconButton(modifier = Modifier.size(40.dp, 40.dp), onClick = {
                            viewModel.setTicketTypeData(
                                index, (viewModel.ticketTypeData.value[index] + 1).toString()
                            )
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.add),
                                contentDescription = "plus"
                            )
                        }
                    }
                }
            }
        }
        Sh(20.dp)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            CustomButton("下一步", 20.dp) {
                nav.navigate("ticket_detail")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TicketDetail() {
    SafeColumn {
        Column(
            modifier = Modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .border(1.dp, ColorLightGray, RoundedCornerShape(10.dp))
                .background(
                    Color.White
                )
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ) {
                LightGrayText("票種", size = 15.sp)
                LightGrayText("價格", size = 15.sp)
                LightGrayText("數量", size = 15.sp)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuyTicketsTopBar(nav: NavController = rememberNavController()) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            nav.navigate("home")
        }, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                tint = ColorBlue,
                contentDescription = "Back"
            )
        }
        Text(
            "2023第41屆新一代設計展",
            modifier = Modifier.align(Alignment.Center),
            color = ColorBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}