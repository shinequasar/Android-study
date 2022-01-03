package com.example.firebasebasic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val dataSource = DataSource()
    fun getMovie() : LiveData<MutableList<Movie>> {
        val data = MutableLiveData<MutableList<Movie>>()
        dataSource.getData().observeForever { //디비로부터 데이터를 가져와만든 오브젝트에 옵저버를 붙임. 변화를 감시하겠다.
            data.value = it
        }
        return data
    }
}