package com.example.musicservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {
    private lateinit var player: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
//startService()에 의해 실행된다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val playing: Boolean = intent?.extras!!.getBoolean("PLAY")
        if(!playing) {
            player = MediaPlayer.create(this, R.raw.music)
            player.start()
            println("Music service is running...")
        }else{
            if(player.isPlaying) player.stop()
            println("Music service is stoped")
        }
        return START_STICKY //서비스가 강제 종료된 이후 다시 재실행하도록 함.
    }
}