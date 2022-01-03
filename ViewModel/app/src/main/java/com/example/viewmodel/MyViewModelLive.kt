package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModelLive : ViewModel() {
    var score: MutableLiveData<Int> = MutableLiveData()
    init{
        score.value = 100 //값을 초기화 하려면 이걸로!
    }

    fun getValue(): Int? = score.value
    fun increaseScore(){
        score.value = score.value?.plus(1)
    }
}