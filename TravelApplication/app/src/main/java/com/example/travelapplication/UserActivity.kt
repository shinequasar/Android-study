package com.example.travelapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.google.android.material.textfield.TextInputEditText

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val nameButton: Button = findViewById(R.id.name_button)

        var mainname: TextInputEditText = findViewById(R.id.editname)
        var mypage_name: TextView = findViewById(R.id.mypage_name)
        var settingButton : Button = findViewById(R.id.setting_button)


        //TODO:  메인페이지에 설정된 이름 가져와서 이름 띄워주기
        if (intent.hasExtra("user")) {
            var userName: String? = intent.getStringExtra("user").toString()
            mypage_name.text = userName
        } else {
            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
            mypage_name.text = "유저이름"
        }


        //TODO:  닉네임 intent로 받아서 메인화면에 welcome문구 보내주기
        nameButton.setOnClickListener{
            mypage_name.text = mainname.text
        }
        settingButton.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("user",mainname.text.toString())
            startActivity(intent)
        }

        //TODO 이미지설정이랑 닉네임설정 버튼할 onclick 함수 두개 만들기
//        val profileButton: Button = findViewById(R.id.profile_button)
//        fun setButtonClickEvent(){
//            nameButton.setOnClickListener{setButtonClickEvent(nameBtn)}
//            profileButton.setOnClickListener{setButtonClickEvent(profileBtn)}
//        }
//        fun onClick(){
//            ActivityResultLauncher(Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI),0)
//        }
    }
}