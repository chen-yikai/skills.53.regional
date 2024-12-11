package com.example.skills53dic

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.skills53dic.screens.MediaCenter

class MediaCenterDetailViewModel : ViewModel() {
    var data = mutableStateOf<MediaCenter?>(null)

    fun setData(newData: MediaCenter) {
        data.value = newData
    }
}
