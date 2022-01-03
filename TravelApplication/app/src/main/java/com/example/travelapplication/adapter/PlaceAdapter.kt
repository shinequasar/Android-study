package com.example.travelapplication.adapter

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.R
import com.example.travelapplication.datamodel.Place

class PlaceAdapter(val context: Context, val placeList:MutableList<Place>?) : RecyclerView.Adapter<PlaceAdapter.MyViewHolder>() {
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var title: TextView = itemview.findViewById(R.id.title)
        var count: TextView = itemview.findViewById(R.id.count)
        var thumbnail: ImageView = itemview.findViewById(R.id.thumbnail)
        var overflow: ImageView = itemview.findViewById(R.id.overflow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { // 뷰 홀더를 만들어 줌.
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.place_card, parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //뷰 홀더에 데이터를 바인딩해 줌.
        val place = placeList!![position]
        holder.title.text = place.name
        holder.count.text = place.numOfplacese.toString()+"테마"
        holder.thumbnail.setImageResource(place.thumbnail)
        holder.overflow.setOnClickListener { showPopupMenu((holder.overflow))}
    }

    private fun showPopupMenu(view:View){
        val popup = PopupMenu(context,view)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_place, popup.menu)
        popup.setOnMenuItemClickListener (MenuListener(context))
        popup.show()
    }

    class MenuListener(context: Context) : PopupMenu.OnMenuItemClickListener{
        val context = context
        override fun onMenuItemClick(p0: MenuItem?): Boolean {
            if(p0?.itemId == R.id.menu_select) showToast()
            if(p0?.itemId == R.id.menu_more) showToast()
            return false
        }

        private fun showToast() {
            val toast: Toast = Toast.makeText(context, "준비 중인 기능입니다.", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, Gravity.CENTER, 250)
            toast.show()
        }
    }


    override fun getItemCount(): Int { //데이터가 몇개나 있는지 알려줌.
        return placeList!!.size
    }
}