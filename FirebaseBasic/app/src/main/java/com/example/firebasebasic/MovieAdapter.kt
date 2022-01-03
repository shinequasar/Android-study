package com.example.firebasebasic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val context: Context)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var mlist: MutableList<Movie> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movie_title)
        val relDate: TextView = view.findViewById(R.id.reldate)
        val runTime: TextView = view.findViewById(R.id.runtime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = mlist[position]
        holder.title.text = movie.title
        holder.relDate.text = movie.reldate
        holder.runTime.text = movie.runtime
        Log.i("Test","Movie : " + movie.reldate + " " + movie.runtime)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    fun setMovieList(data: MutableList<Movie>) {
        mlist = data
    }
}