package com.example.travelapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class FoodDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        var foodname: TextView = findViewById(R.id.detail_title)
        var foodimg: ImageView = findViewById(R.id.detail_img)
        var content: TextView = findViewById(R.id.detail_contents)

        if (intent.hasExtra("food_name")) {
            foodname.text = intent.getStringExtra("food_name").toString()
            content.text = intent.getStringExtra("food_content").toString()
            val bundle: Bundle? = intent.extras
            val resId: Int = bundle!!.getInt("food_thumbnail")
            foodimg.setImageResource(resId)
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
}
