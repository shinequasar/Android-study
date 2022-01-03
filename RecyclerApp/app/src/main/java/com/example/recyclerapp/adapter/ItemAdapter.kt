package com.example.recyclerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapp.R
import com.example.recyclerapp.datamodel.Affirmation

class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        // Inner class Item ViewHolder
        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
            val textView: TextView = view.findViewById(R.id.item_title)
            val imageView: ImageView = view.findViewById(R.id.item_title)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itmelayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(itmelayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.strResource)
        holder.imageView.setImageResource(item.imageResource)
    }

    override fun getItemCount() = dataset.size  // 람다식
}