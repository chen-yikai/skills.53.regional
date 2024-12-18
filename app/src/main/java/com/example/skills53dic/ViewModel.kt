package com.example.skills53dic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.skills53dic.screens.MediaCenter

class MediaCenterDetailViewModel : ViewModel() {
    var data = mutableStateOf<MediaCenter?>(null)

    fun setData(newData: MediaCenter) {
        data.value = newData
    }
}

class AddTicketViewModel : ViewModel() {
    var type = mutableStateOf("")
    var name = mutableStateOf("")
    var email = mutableStateOf("")
    var phone = mutableStateOf("")
    var date = mutableStateOf("")
    var ticketId = mutableStateOf("")
    var price = mutableStateOf("")

    fun qrcodeRead(){

    }
}