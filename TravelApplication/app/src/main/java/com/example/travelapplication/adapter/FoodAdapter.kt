package com.example.travelapplication.adapter

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.FoodDetailsActivity
import com.example.travelapplication.R
import com.example.travelapplication.datamodel.Food
import java.io.ByteArrayOutputStream

class FoodAdapter(val context: Context, val foodList:MutableList<Food>?) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>()  {
    class FoodViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var title: TextView = itemview.findViewById(R.id.food_name)
        var content: TextView = itemview.findViewById(R.id.food_content)
        var thumbnail: ImageView = itemview.findViewById(R.id.food_cover)
        var more: Button = itemview.findViewById(R.id.more_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.food_card, parent, false)
        return FoodAdapter.FoodViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList!![position]
        holder.title.text = food.name
        holder.content.text = food.content
        holder.thumbnail.setImageResource(food.thumbnail)
        holder.more.setOnClickListener{(showDetails(food))}
    }

    private fun showDetails(foodList: Food) {
        var intent = Intent(context, FoodDetailsActivity::class.java)


        intent.putExtra("food_name",foodList.name)
        intent.putExtra("food_content",foodList.content)
        intent.putExtra("food_thumbnail",foodList.thumbnail)
        context.startActivities(arrayOf(intent))
    }


    override fun getItemCount(): Int { //데이터가 몇개나 있는지 알려줌.
        return foodList!!.size
    }
}