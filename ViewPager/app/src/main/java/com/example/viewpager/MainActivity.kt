package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private var mlist: MutableList<Int> = mutableListOf() //포스터 데이터들

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = findViewById(R.id.viewpager)  //뷰페이저랑 어댑터랑 연결하기
        //adapter = ViewPagerAdapter(mlist)
        //pager.adapter = adapter
        pager.adapter = FragmentSlideAdapter(this) //현재 mainActivity가 뷰페이져 가지고있으니까
        //prepare()
    }
//    fun prepare(){
//        mlist.add(R.drawable.movie1)
//        mlist.add(R.drawable.movie2)
//        mlist.add(R.drawable.movie3)
//        mlist.add(R.drawable.movie4)
//        mlist.add(R.drawable.movie5)
//        mlist.add(R.drawable.movie6)
//        mlist.add(R.drawable.movie7)
//        mlist.add(R.drawable.movie8)
//
//        adapter.notifyDataSetChanged() //mlist 데이터가 변경되었음을 알림
//    }

}