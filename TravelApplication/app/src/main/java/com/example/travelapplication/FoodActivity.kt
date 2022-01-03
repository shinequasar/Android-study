package com.example.travelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.adapter.FoodAdapter
import com.example.travelapplication.adapter.PlaceAdapter
import com.example.travelapplication.datamodel.Food
import com.example.travelapplication.datamodel.Place

class FoodActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapter: FoodAdapter? = null
    var foodList: MutableList<Food>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        recyclerView = findViewById(R.id.recycler_view)
        adapter = FoodAdapter(this, foodList)
        recyclerView?.layoutManager = GridLayoutManager(this,1)
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
        prepareFoods()
    }

    private fun prepareFoods() {
        val covers = intArrayOf(
            R.drawable.food1,
            R.drawable.food2,
            R.drawable.food3,
            R.drawable.food4,
            R.drawable.food5,
            R.drawable.food6,
            R.drawable.food7,
            R.drawable.food8,
            R.drawable.food9,
            R.drawable.food10,
            R.drawable.food11)

        var a = Food(getString(R.string.food1), getString(R.string.content1), covers[0])
        foodList!!.add(a)
        a =  Food(getString(R.string.food2), getString(R.string.content2), covers[1])
        foodList!!.add(a)
        a = Food(getString(R.string.food3), getString(R.string.content3), covers[2])
        foodList!!.add(a)
        a = Food(getString(R.string.food4), getString(R.string.content4), covers[3])
        foodList!!.add(a)
        a =  Food(getString(R.string.food5), getString(R.string.content5), covers[4])
        foodList!!.add(a)
        a = Food(getString(R.string.food6), getString(R.string.content6), covers[5])
        foodList!!.add(a)
        a =  Food(getString(R.string.food7), getString(R.string.content7), covers[6])
        foodList!!.add(a)
        a =  Food(getString(R.string.food8), getString(R.string.content8), covers[7])
        foodList!!.add(a)
        a = Food(getString(R.string.food9), getString(R.string.content9), covers[8])
        foodList!!.add(a)
        a =  Food(getString(R.string.food10), getString(R.string.content10), covers[9])
        foodList!!.add(a)
        a =  Food(getString(R.string.food11), getString(R.string.content11), covers[10])
        foodList!!.add(a)
        adapter!!.notifyDataSetChanged()
    }
}