package com.example.databinding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.databinding.BindingActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SimpleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.binding_activity)
        val binding: BindingActivityBinding = DataBindingUtil.setContentView(this, R.layout.binding_activity)
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
        binding.viewmodel = viewModel
        updateLikes()
//        binding.name = "James"
//        binding.lastName = "Bond"

//        val name:TextView = findViewById(R.id.plain_name)
//        name.text = "James"

    }

    fun onLike(view: View) {
        viewModel.onLike() //실제 데이터는 여기서 변경중
        updateLikes()
    }

    private fun updateName() {
        findViewById<TextView>(R.id.plain_name).text = viewModel.name
        findViewById<TextView>(R.id.plain_lastname).text = viewModel.lastName
    }


    private fun updateLikes() {
        findViewById<TextView>(R.id.likes).text = viewModel.likes.toString()
        findViewById<ProgressBar>(R.id.progressBar).progress =
            (viewModel.likes * 100 / 5).coerceAtMost(100)
        val image = findViewById<ImageView>(R.id.imageView)
        val color = getAssociatedColor(viewModel.popularity, this)
        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(color))
        image.setImageDrawable(getDrawablePopularity(viewModel.popularity, this)) //아이콘을 가져와 보여줌
    }

    private fun getAssociatedColor(popularity: Popularity, context: Context): Int {
        return when (popularity) {
            Popularity.NORMAL -> context.theme.obtainStyledAttributes(
                intArrayOf(android.R.attr.colorForeground)
            ).getColor(0, 0x000000)
            Popularity.POPULAR -> ContextCompat.getColor(context, R.color.popular)
            Popularity.STAR -> ContextCompat.getColor(context, R.color.star)
        }
    }


    private fun getDrawablePopularity(popularity: Popularity, context: Context): Drawable? {
        return when (popularity) {
            Popularity.NORMAL -> {
                ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp)
            }
            Popularity.POPULAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
            }
            Popularity.STAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
            }
        }
    }


}