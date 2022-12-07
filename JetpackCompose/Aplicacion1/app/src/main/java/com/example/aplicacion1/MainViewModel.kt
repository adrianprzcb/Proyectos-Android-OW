package com.example.aplicacion1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val textFieldState = MutableLiveData("")

    fun onTextField(newText : String){
        textFieldState.value = newText
    }
}