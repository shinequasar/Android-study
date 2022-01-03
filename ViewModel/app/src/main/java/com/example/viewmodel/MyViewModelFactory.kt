package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MyViewModelFactory(private var value: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(value) as T //as T 타입변환 //factory에서 인자를 받아 그 인자로 뷰모델을 생성해줌
        }
        throw IllegalArgumentException ("Undefind ViewModel class")
    }
}