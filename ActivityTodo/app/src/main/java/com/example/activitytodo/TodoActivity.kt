package com.example.activitytodo

import MyAdapter
import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytodo.databinding.ActivityAddBinding

class TodoActivity : AppCompatActivity(){
    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.menu_add_save -> {
                val intent = intent
                intent.putExtra("result", binding.addEditView.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
                true
            }
            else -> true
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}