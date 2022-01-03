package com.example.firebasebasic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataSource {
    fun getData(): LiveData<MutableList<Movie>> {
        val liveMovies = MutableLiveData<MutableList<Movie>>()
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("movies")
        ref.addValueEventListener(object : ValueEventListener {
            val mlist = mutableListOf<Movie>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    for(movieSnapshot in snapshot.children) {
                        val data = movieSnapshot.getValue(Movie::class.java)  //읽어온 데이터로 movie 오브젝트 생성
                        mlist.add(data!!)
                    }
                    liveMovies.value = mlist  //라이브 데이터로 넣고 리턴
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return liveMovies
    }
}