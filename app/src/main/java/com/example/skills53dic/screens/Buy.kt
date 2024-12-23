package com.example.skills53dic.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.room.util.TableInfo
import com.example.skills53dic.BuyTicketViewModel
import com.example.skills53dic.R
import com.example.skills53dic.components.BlueText
import com.example.skills53dic.components.BuyBox
import com.example.skills53dic.components.ColorBlue
import com.example.skills53dic.components.ColorLightGray
import com.example.skills53dic.components.ContactInput
import com.example.skills53dic.components.CustomButton
import com.example.skills53dic.components.CustomTextButton
import com.example.skills53dic.components.GreenText
import com.example.skills53dic.components.LightGrayText
import com.example.skills53dic.components.SafeColumn
import com.example.skills53dic.components.Sh
import com.example.skills53dic.components.Sw
import com.example.skills53dic.components.TableFucker
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import android.util.Patterns

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyTicket(RootNav: NavController = rememberNavController()) {
    val BuyNav = rememberNavController()
    val viewModel = BuyTicketViewModel()
    Scaffold(topBar = {
        BuyTicketsTopBar(RootNav, BuyNav)
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavHost(navController = BuyNav, startDestination = "ticket_type") {
                composable("ticket_type") {
                    TicketType(BuyNav, viewModel)
                }

                composable("ticket_detail") {
                    TicketDetail(viewModel, BuyNav)
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableFucker("票種", ColorLightGray)
                TableFucker("價格", ColorLightGray)
                TableFucker(
                    "數量", ColorLightGray, customWidth = 120.dp, alignment = Alignment.Center
                )
            }
            data.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableFucker(item.ticketType)
                    TableFucker("NT " + item.price.toString())
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
            CustomButton("下一步", 20.dp, modifier = Modifier, onClick = {
                nav.navigate("ticket_detail")
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Preview(showBackground = true)
@Composable
fun TicketDetail(
    viewModel: BuyTicketViewModel = viewModel(), nav: NavController = rememberNavController()
) {
    val context = LocalContext.current
    val gson = Gson()
    val dataJson: String = context.assets.open("ticket_type.json").use { inputStream ->
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readText()
        }
    }
    val paymentMethodsJson: String = context.assets.open("payment_method.json").use {
        BufferedReader(InputStreamReader(it)).use {
            it.readText()
        }
    }
    val data = gson.fromJson(dataJson, Array<TicketTypeSchema>::class.java)
    val paymentMethods = gson.fromJson(paymentMethodsJson, Array<String>::class.java)
    val totalCount = rememberSaveable { mutableStateOf<Int>(0) }
    var syntaxError = false
    val name = remember { mutableStateOf("") }
    var nameError = if (name.value.isEmpty()) {
        ""
    } else if (name.value.contains(" ")) {
        syntaxError = true
        "勿包含空白字元"
    } else {
        ""
    }
    val email = remember { mutableStateOf("") }
    var emailError = if (email.value.isEmpty()) {
        ""
    } else if (email.value.contains(" ")) {
        syntaxError = true
        "勿包含空白字元"
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
        syntaxError = true
        "格式錯誤"
    } else {
        ""
    }
    val phone = remember { mutableStateOf("") }
    var phoneError = if (phone.value.isEmpty()) {
        ""
    } else if (phone.value.contains(" ")) {
        syntaxError = true
        "勿包含空白字元"
    } else if (!Regex("^09[0-9]{8}$").matches(phone.value)) {
        syntaxError = true
        "格式錯誤"
    } else {
        ""
    }
    val payment = remember { mutableStateOf("") }
    var paymentDropDownShow = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val showDatePicker = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val todayMillis = calendar.timeInMillis
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val today = dateFormat.format(Date(todayMillis))

    LaunchedEffect(data) {
        if (data.isNotEmpty()) {
            totalCount.value =
                data.sumOf { it.price * viewModel.ticketTypeData.value[data.indexOf(it)] }
        }
    }

    if (showDatePicker.value) {
        DatePickerDialog(onDismissRequest = { showDatePicker.value = false }, confirmButton = {
            Button(onClick = {
                val milliseconds = datePickerState.selectedDateMillis
                val userFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                val tempSelectDate = dateFormat.format(Date(milliseconds!!))
                if (tempSelectDate < today) {
                    toast("請輸入今日以後的日期", context)
                } else {
                    selectedDate.value = userFormat.format(Date(milliseconds))
                    showDatePicker.value = false
                }
            }) {
                Text("OK")
            }
        }, dismissButton = {
            Button(onClick = {
                showDatePicker.value = false
            }) {
                Text("Cancel")
            }
        }) {
            DatePicker(
                state = datePickerState,
            )
        }
    }

    SafeColumn {
        Column(
            modifier = Modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .border(1.dp, ColorLightGray, RoundedCornerShape(10.dp))
                .background(
                    Color.White
                )
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableFucker("票種", ColorLightGray)
                TableFucker("票種", ColorLightGray)
                TableFucker("數量", ColorLightGray)
            }
            LazyColumn {
                itemsIndexed(data) { index, item ->
                    if (viewModel.ticketTypeData.value[index] != 0) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            TableFucker(item.ticketType)
                            TableFucker("NT " + item.price.toString())
                            TableFucker(viewModel.ticketTypeData.value[index].toString())
                        }
                    }
                }
            }
            Sh(5.dp)
            HorizontalDivider()
            Sh(5.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LightGrayText("總額", 15.sp)
                BlueText("NT " + totalCount.value.toString(), 15.sp)
            }
        }
        Sh(20.dp)
        Text("購買人資料", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        Sh(10.dp)
        Column {
            ContactInput(name, "姓名", errorMessage = nameError) {
                if (it.length <= 15) {
                    name.value = it
                } else {
                    toast("姓名不可超過15字元", context)
                }
            }
            ContactInput(email, "電子郵件", errorMessage = emailError) {
                if (it.length <= 30) {
                    email.value = it
                } else {
                    toast("Email不可超過30字元", context)
                }
            }
            ContactInput(phone, "電話", errorMessage = phoneError) {
                phone.value = it
            }
            BuyBox(text = selectedDate, label = "日期") {
                showDatePicker.value = true
            }
            Column() {
                BuyBox(text = payment, label = "付款方式") {
                    paymentDropDownShow.value = true
                }
                DropdownMenu(
                    expanded = paymentDropDownShow.value,
                    onDismissRequest = { paymentDropDownShow.value = false },
                ) {
                    paymentMethods.forEach {
                        DropdownMenuItem(text = {
                            Text(it, fontSize = 15.sp)
                        }, onClick = {
                            payment.value = it
                            paymentDropDownShow.value = false
                        })
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                CustomTextButton("上一步") {
                    nav.popBackStack()
                }
                Sw(10.dp)
                CustomButton("確認購買", padding = 10.dp) {
                    if (name.value.isEmpty() || email.value.isEmpty() || phone.value.isEmpty() || selectedDate.value.isEmpty() || payment.value.isEmpty()) {
                        toast("請填寫完整表單", context)
                    } else if (syntaxError) {
                        toast("表單格式有誤", context)
                    } else {
                        toast("購買成功", context)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BuyTicketsTopBar(
    RootNav: NavController = rememberNavController(),
    BuyNav: NavController = rememberNavController()
) {
    val navBackStackEntry by BuyNav.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            if (currentRoute == "ticket_type") {
                RootNav.navigate("home")
            } else {
                BuyNav.navigate("ticket_type")
            }
        }, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                tint = ColorBlue,
                contentDescription = "Back"
            )
        }
        BlueText(
            "2023第41屆新一代設計展",
            weight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}