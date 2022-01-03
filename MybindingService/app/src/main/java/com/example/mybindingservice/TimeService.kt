package com.example.mybindingservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class TimeService : Service() {
    val binder: IBinder = MyBinder()
    //bindService() 호출에 따라 실행
    override fun onBind(intent: Intent): IBinder {
        println("onBind() method is running...")
        return binder
    }

    fun getCurrentTime() : String{
        val timeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.KOREA)
        return timeFormat.format(Date())
    }

    inner class MyBinder : Binder(){
        fun getService(): TimeService = this@TimeService // 자신의 객체를 반환
    }
}