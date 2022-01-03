package com.example.viewmodel

import androidx.lifecycle.ViewModel

class MyViewModel(private var counter: Int) : ViewModel() { //받아올 인자가 있는 뷰모델은 프로바이더로만으로 못생성. --> 팩토리 필요
//    private var counter = 0 //Data
    fun getCurrentVal() = counter
    fun increaseCounter() = ++counter
}
