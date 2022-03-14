package com.orbitalsonic.sharedviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel2 : ViewModel() {
    val message = MutableLiveData<String>()

    fun sendMessage(text: String) {
        message.value = text
    }
}