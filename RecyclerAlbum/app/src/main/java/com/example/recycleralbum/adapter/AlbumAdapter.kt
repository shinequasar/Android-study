package com.example.recycleralbum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleralbum.R
import com.example.recycleralbum.datamodel.Album

class AlbumAdapter(val context: Context, val albumList: MutableList<Album>?) : RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var title: TextView = itemview.findViewById(R.id.title)
        var count: TextView = itemview.findViewById(R.id.count)
        var thumbnail : ImageView = itemview.findViewById(R.id.thumbnail)
        //var thumbnail : MenuView.ItemView = itemview.findViewById(R.id.thumbnail)
        var overflow: ImageView = itemview.findViewById(R.id.overflow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.album_card,parent,false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val album = albumList!![position]
        holder.title.text = album.name
        holder.count.text = album.numOfSongs.toString() + "songs"
        holder.thumbnail.setImageResource(album.thumbnail)
        holder.overflow.setOnClickListener { showPopupMenu((holder.overflow))}
    }

    private fun showPopupMenu(view:View){
        val popup = PopupMenu(context,view)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_album, popup.menu)
        popup.setOnMenuItemClickListener (MenuListener())
        popup.show()
    }

    class MenuListener : PopupMenu.OnMenuItemClickListener{
        override fun onMenuItemClick(p0: MenuItem?): Boolean {
            return false
        }
    }

    override fun getItemCount(): Int {
        return albumList!!.size
    }
}