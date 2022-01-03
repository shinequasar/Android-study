package com.example.firestorenotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestoreimport.NoteAdapter
import com.example.firestorenotes.adapter.NoteAdapter
import com.example.firestorenotes.databinding.ActivityMainBinding
import com.example.firestorenotes.model.Note
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteAdapter
    private lateinit var database: FirebaseFirestore
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = FirebaseFirestore.getInstance() // DB reference
        loadNotes()

    }

    // DB에서 데이터 검색한 다음 데이터 리스트를 생성하고 어댑터에 전달
    private fun loadNotes(){
        database.collection("notes").get().addOnCanceledListener {
            task -> if(task.isSuccessful){
                val notesList = mutableListOf<Note>()
            for(doc in task.result!!){
                val note = doc.toObject(Note::class.java)  //Note 객체로 생성
                notesList.add(note)
            }
            adapter = NoteAdapter(notesList, applicationContext, database)
            binding.rvNoteList.layoutManager = LinearLayoutManager(applicationContext)
            binding.rvNoteList.itemAnimator = DefaultItemAnimator()
            binding.rvNoteList.adapter = adapter
        }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null && item.itemId == R.id.addNote){
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}