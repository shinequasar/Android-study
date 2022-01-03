package com.example.viewpager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.viewpager.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "poster"
private const val ARG_PARAM2 = "title"
private const val ARG_PARAM3 = "desc"


/**
 * A simple [Fragment] subclass.
 * Use the [PosterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PosterFragment : Fragment() {
    private var param1: Int? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {  //번들(여러가지 타입의 값을 저장하는 map클래스)에서 꺼내오는거
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView( //뷰를 만들때 현재 데이터에 들어있는 걸 넣어주기
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_poster, container, false)
        view.findViewById<ImageView>(R.id.frag_poster).setImageResource(param1!!)
        return view
    }

    companion object { //싱글톤. 메모리에 하나만 존재하게해서 메모리낭비 줄임
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PosterFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int, param2: String,param3: String) =
            PosterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }
}