package com.example.firebasebasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieAdapter(this)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        setData()
    }

    fun setData() {
        viewModel.getMovie().observe(this, Observer {  //옵저버기능 등록
            adapter.setMovieList(it)  //데이터 넣어주기
            adapter.notifyDataSetChanged()
        })
    }
}