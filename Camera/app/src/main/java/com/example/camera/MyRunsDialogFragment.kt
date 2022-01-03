package com.example.camera

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyRunsDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val id = requireArguments().getInt("id")
        val parent: Activity? = activity
        return when(id){
            ID_PHOTO_PICKER_FROM_CAMERA -> {
                val builder = AlertDialog.Builder(parent!!)
                builder.setTitle(R.string.ui_profile_photo_picker_title)

                val click = DialogInterface.OnClickListener{ dialogInterface, i -> //다이얼로그가 사라지며 작동되게
                    (parent as MainActivity?)?.onPhotoPickerItemSelected(i)} //Activity 타입인 parent에 MainActivity로 받아와 .onPhotoPicker~ 함수실행
                builder.setItems(R.array.ui_profile_photo_picker_items,click)
                builder.create()
            }
            else -> {
                val builder = AlertDialog.Builder(parent!!)
                builder.setTitle("Unknown")
                builder.create()
            }
        }
    }

    companion object{
        const val ID_PHOTO_PICKER_FROM_CAMERA = 0
        fun newInstance(id : Int): MyRunsDialogFragment{
            val fragment = MyRunsDialogFragment()
            var args = Bundle()
            args.putInt("id",id) //key,value
            fragment.arguments =args
            return fragment
        }
    }
}