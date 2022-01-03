package com.example.camera

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var mImageCaptureUri: Uri? = null
    private var mImageView: ImageView? = null
    private var isTakenFromCamera = false
    private var rotatedBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mImageView = findViewById(R.id.imageProfile)
        checkPermissions()
    }

    //퍼미션 검사
    private fun checkPermissions(){
        if(Build.VERSION.SDK_INT < 23) return  //23이하 버전은 pass
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED // 이 두가지가 아니면
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0) //이걸리턴
        }
    }

    //위의 requestPermission 실행결과에 대해 처리
    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){}//사용자가 퍼미션을 허용한 경우
        else { //사용자가 퍼미션을 거부한 경우
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    val builder = AlertDialog.Builder(this) //사용자에게 왜 퍼미션이 필요한지 알람 띄우기
                    builder.setMessage("This permission is very important the app.").setTitle("Request Permission")
                    builder.setPositiveButton("OK"){ //사용자가 ok하면 다시 requestPermissions
                        dialog, id -> requestPermissions(arrayOf(Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
                    }
                }
                else{}
            }
        }
    }

    // Take Photo button clicked
    fun onChangePhotoClicked(v: View?) {
        val fragment: DialogFragment = MyRunsDialogFragment.newInstance(MyRunsDialogFragment.ID_PHOTO_PICKER_FROM_CAMERA)
        fragment.show(supportFragmentManager, getString(R.string.dialog_fragment_tag_photo_picker))
    }

    // 다이얼로그 창에서 Take photo 선택했을 때 실행
    fun onPhotoPickerItemSelected(item: Int) {
        val intent: Intent // 묵시적인 인텐트 : 카메라 앱을 실행하기 위한 인텐트
        when (item) {
            MyRunsDialogFragment.ID_PHOTO_PICKER_FROM_CAMERA -> {
                intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 인텐트에 무엇을 하고싶은지 지정
                val values = ContentValues(1)
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                mImageCaptureUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri)
                intent.putExtra("return-data", true)
                startActivityForResult(intent, 0)
                isTakenFromCamera = true
            }
            else -> return
        }
    }

    // 카메라앱에서 사진을 찍고 돌아왔을 때
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) return
        when (requestCode) {
            0 -> beginCrop(mImageCaptureUri)
            Crop.REQUEST_CROP -> {
                println("handleCrop() :" + Crop.REQUEST_CROP)
                handleCrop(resultCode, data)
                if (isTakenFromCamera) {
                    val f = File(mImageCaptureUri!!.path)
                    if (f.exists()) f.delete()
                }
            }
        }
    }

    private fun beginCrop(source: Uri?) {
        val destination = Uri.fromFile(File(cacheDir, "cropped"))
        Crop.of(source, destination).asSquare().start(this)
    }

    private fun handleCrop(resultCode: Int, result: Intent?) {
        if (resultCode == RESULT_OK) {
            val uri = Crop.getOutput(result)
            val bitmap: Bitmap
            try {
                bitmap = if (Build.VERSION.SDK_INT < 28) MediaStore.Images.Media.getBitmap(this.contentResolver, uri) else {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, uri))
                }
                mImageView!!.setImageBitmap(imageOreintationValidator(bitmap, uri.path))
            } catch (e: Exception) {
                Log.d("Error", "error")
            }
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun imageOreintationValidator(bitmap: Bitmap, path: String?): Bitmap? {
        val ei: ExifInterface
        try {
            ei = ExifInterface(path!!)
            val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED)
            rotatedBitmap = null
            rotatedBitmap = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
                ExifInterface.ORIENTATION_NORMAL -> bitmap
                else -> bitmap
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return rotatedBitmap
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
            matrix, true)
    }

    fun onSaveClicked(view: View?) {}
}