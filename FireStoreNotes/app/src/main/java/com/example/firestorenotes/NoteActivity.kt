package com.example.firestorenotes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firestorenotes.databinding.ActivityNoteBinding
import com.example.firestorenotes.model.Note
import com.google.firebase.firestore.FirebaseFirestore

class NoteActivity  : AppCompatActivity() {
    private val TAG = "AddNoteActivity"
    private var firestoreDB: FirebaseFirestore? = null
    internal var id: String = ""
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestoreDB = FirebaseFirestore.getInstance()

        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("UpdateNoteId").toString()

            binding.edtTitle.setText(bundle.getString("UpdateNoteTitle"))
            binding.edtContent.setText(bundle.getString("UpdateNoteContent"))
        }

        binding.btAdd.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val content = binding.edtContent.text.toString()

            if (title.isNotEmpty()) {
                if (id.isNotEmpty()) {
                    updateNote(id, title, content)
                } else {
                    addNote(title, content)
                }
            }

            finish()
        }
    }

    private fun updateNote(id: String, title: String, content: String) {
        val note = Note(id, title, content).toMap()

        firestoreDB!!.collection("notes")
            .document(id)
            .set(note)
            .addOnSuccessListener {
                Log.e(TAG, "Note document update successful!")
                Toast.makeText(applicationContext, "Note has been updated!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding Note document", e)
                Toast.makeText(applicationContext, "Note could not be updated!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addNote(title: String, content: String) {
        val note = Note(title, content).toMap()

        firestoreDB!!.collection("notes")
            .add(note)
            .addOnSuccessListener { documentReference ->
                Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.id)
                Toast.makeText(applicationContext, "Note has been added!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding Note document", e)
                Toast.makeText(applicationContext, "Note could not be added!", Toast.LENGTH_SHORT).show()
            }
    }
}