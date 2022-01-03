package com.example.travelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.adapter.PlaceAdapter
import com.example.travelapplication.datamodel.Place

class PlaceActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapter: PlaceAdapter? = null
    var placeList:MutableList<Place>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        supportActionBar?.hide()
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = PlaceAdapter(this, placeList)
        recyclerView?.layoutManager = GridLayoutManager(this,2)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        findViewById<ImageView>(R.id.backdrop).setImageResource(R.drawable.cover2)
        preparePlacese()
    }

    private fun preparePlacese() {
        val covers = intArrayOf(
            R.drawable.place1,
            R.drawable.place2,
            R.drawable.place3,
            R.drawable.place4,
            R.drawable.place5,
            R.drawable.place6,
            R.drawable.place7,
            R.drawable.place8,
            R.drawable.place9,
            R.drawable.place10,
            R.drawable.place11)

        var a = Place(getString(R.string.tour1), 1, covers[0])
        placeList!!.add(a)
        a = Place(getString(R.string.tour2), 11, covers[1])
        placeList!!.add(a)
        a = Place(getString(R.string.tour3), 12, covers[2])
        placeList!!.add(a)
        a = Place(getString(R.string.tour4), 10, covers[3])
        placeList!!.add(a)
        a = Place(getString(R.string.tour5), 5, covers[4])
        placeList!!.add(a)
        a = Place(getString(R.string.tour6), 7, covers[5])
        placeList!!.add(a)
        a = Place(getString(R.string.tour7), 17, covers[6])
        placeList!!.add(a)
        a = Place(getString(R.string.tour8), 17, covers[7])
        placeList!!.add(a)
        a = Place(getString(R.string.tour9), 11, covers[8])
        placeList!!.add(a)
        a = Place(getString(R.string.tour10), 17, covers[9])
        placeList!!.add(a)
        adapter!!.notifyDataSetChanged()

    }
}