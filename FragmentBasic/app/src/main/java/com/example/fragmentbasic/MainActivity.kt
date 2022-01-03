package com.example.fragmentbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.fragmentbasic.fragment.ElepFragment
import com.example.fragmentbasic.fragment.LionFragment

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragmentContainerView)

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener{ //fragmentContainerView 위치에 lion 넣어라
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, LionFragment()).commit()
        }
        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ElepFragment()).commit()
        }
    }
}