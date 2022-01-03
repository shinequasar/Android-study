package com.example.layoutdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var dialPad: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialPad = findViewById(R.id.tv_dial)
    }

    fun dialClick(view: View){
        var number: String = dialPad?.text.toString()
        when (view.id){
            R.id.t0 ->dialPad?.text = number + "0"
            R.id.t1 ->dialPad?.text = number + "1"
            R.id.t2 ->dialPad?.text = number + "2"
            R.id.t3 ->dialPad?.text = number + "3"
            R.id.t4 ->dialPad?.text = number + "4"
            R.id.t5 ->dialPad?.text = number + "5"
            R.id.t6 ->dialPad?.text = number + "6"
            R.id.t7 ->dialPad?.text = number + "7"
            R.id.t8 ->dialPad?.text = number + "8"
            R.id.t9 ->dialPad?.text = number + "9"
            R.id.icon_back -> dialPad?.text = number.substring(0, number.length-1)
        }
    }

}