package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var viewModel: MyViewModel
    private lateinit var viewModelFactory: MyViewModelFactory
    private lateinit var viewModelLive: MyViewModelLive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModelFactory = MyViewModelFactory(50)  //50으로 시작
        //viewModel = MyViewModel() 싱글톤하려고 이렇게 생성x
        //viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)  //싱글톤. 인스턴스화를 프로바이더로 함
        viewModelLive = ViewModelProvider(this).get(MyViewModelLive::class.java) //싱글톤. 인스턴스화를 프로바이더로 함
        textView = findViewById(R.id.textView)
        //textView.text = viewModel.getCurrentVal().toString()
        findViewById<Button>(R.id.button).setOnClickListener{
            //counter++  //Data processing code
            //textView.text = viewModel.increaseCounter().toString() //Bind data to view
            viewModelLive.increaseScore()
        }
        val myObserver = Observer<Int>{  //정수에 대한 옵저버 생성. 감시. 바뀐 값을 잡아서 텍스트뷰에 표시
            textView.text = it.toString()
        }
        viewModelLive.score.observe(this, myObserver)
    }
}