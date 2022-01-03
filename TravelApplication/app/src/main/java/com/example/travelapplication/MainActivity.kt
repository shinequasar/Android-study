package com.example.travelapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO user_activity에서 닉네임 받아와서 welcome 문구 적어주기
        val nameview: TextView = findViewById(R.id.welcom)
        var userName: String? = "유저이름"
        if (intent.hasExtra("user")) {
            userName = intent.getStringExtra("user").toString()
            nameview.text = userName + "님 환영합니다"
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
            nameview.text = "마이페이지에서 이름을 등록해주세요"
        }

        val placeButton: Button = findViewById(R.id.place_button)
        val foodButton: Button = findViewById(R.id.food_button)
        val userButton: Button = findViewById(R.id.user_button)

        placeButton.setOnClickListener{
            val intent = Intent(applicationContext, PlaceActivity::class.java)
            startActivity(intent)
        }
        foodButton.setOnClickListener{
            val intent = Intent(applicationContext, FoodActivity::class.java)
            startActivity(intent)
        }
        userButton.setOnClickListener{
            val intent = Intent(applicationContext, UserActivity::class.java)
            intent.putExtra("user",userName)
            startActivity(intent)
        }


    }
}