package com.example.databinding

import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    val name = "James"
    val lastName = "Bond"
    var likes = 0
        private set //setLikes() 를 만들지 않음. 외부에서 likes 변경을 할 수 없음.
    val popularity: Popularity
        get() {
            return when{
                likes > 9 -> Popularity.STAR
                likes > 4 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
    fun onLike(){
        likes++
    }
}

enum class Popularity{
    NORMAL, POPULAR, STAR
}