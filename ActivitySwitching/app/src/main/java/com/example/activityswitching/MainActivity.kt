package com.example.activityswitching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult

class MainActivity : AppCompatActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener{
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("age",23)
//            startActivityForResult(intent, REQ_CODE)
            launcher.launch(intent)
        }
        launcher = registerForActivityResult(ActivityResultContracts.startActivityForResult()){
            result: ActivityResult -> if(result.resultCode == RESULT_OK){
            val nameview: TextView=findViewById(R.id.name)
            nameview.text = result.data!!.getStringExtra("name")
        }
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQ_CODE && resultCode == RESULT_OK){
//            val nameview: TextView=findViewById(R.id.name)
//            nameview.text = data!!.getStringExtra("name")
//        }
//    }
//
//    companion object{ //클래스변수
//        const val REQ_CODE =10
//    }
}