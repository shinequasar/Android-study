package com.example.recycleralbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleralbum.adapter.AlbumAdapter
import com.example.recycleralbum.datamodel.Album

class MainActivity : AppCompatActivity() {
    private var recyclerView:RecyclerView? =null
    private var adapter:AlbumAdapter?=null
    private var albumList: MutableList<Album>?= mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        supportActionBar?.hide()
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = AlbumAdapter(this, albumList)
        recyclerView?.layoutManager = GridLayoutManager(this,2)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        findViewById<ImageView>(R.id.backdrop).setImageResource(R.drawable.cover)
        prepareAlbums()
    }



    private fun prepareAlbums() {
        val covers = intArrayOf(
            R.drawable.album1,
            R.drawable.album2,
            R.drawable.album3,
            R.drawable.album4,
            R.drawable.album5,
            R.drawable.album6,
            R.drawable.album7,
            R.drawable.album8,
            R.drawable.album9,
            R.drawable.album10,
            R.drawable.album11)
        var a = Album("Love poem", 1, covers[0])
        albumList!!.add(a)
        a = Album("CHANNEL 8", 11, covers[1])
        albumList!!.add(a)
        a = Album("Purpose-The 2nd Album", 12, covers[2])
        albumList!!.add(a)
        a = Album("항해", 10, covers[3])
        albumList!!.add(a)
        a = Album("Rewind", 5, covers[4])
        albumList!!.add(a)
        a = Album("Feel Special", 7, covers[5])
        albumList!!.add(a)
        a = Album("Speak Your Mind", 17, covers[6])
        albumList!!.add(a)
        a = Album("I met you when I was 18", 17, covers[7])
        albumList!!.add(a)
        a = Album("Hello", 11, covers[8])
        albumList!!.add(a)
        a = Album("Greatest Hits", 17, covers[9])
        albumList!!.add(a)
        adapter!!.notifyDataSetChanged()
    }
}