package com.example.mybindingservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var timeService: TimeService
    private var isBound = false //바인드 되었나 표시
    private val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as TimeService.MyBinder //Downcasting
            timeService = binder.getService()
            isBound = true
            println("onServiceConnected() method is performed.")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener{
            if(isBound)// 잘 바인드 되었나 확인하기
            findViewById<TextView>(R.id.textview).text = timeService.getCurrentTime()
            else
                findViewById<TextView>(R.id.textview).text = "Not connected to TimeService"
        }
    }

    override fun onStart() { //onCreate에 넣으면 x. 여기에 넣어야 unbind했다 bind했다 하는거!
        super.onStart()
        Intent(this, TimeService::class.java).also { intent -> //오브젝트 만들고 이어서 람다식
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // -->onBind() --> MyBinder 객체 반환
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}