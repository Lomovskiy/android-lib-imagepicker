package com.lomovskiy.lib.imagepicker.sample

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.lomovskiy.lib.imagepicker.PickImageFromCameraContract
import com.lomovskiy.lib.imagepicker.PickImageFromGalleryContract
import com.lomovskiy.lib.ui.showToast
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    companion object {

        const val TAG = "MainActivity"

    }

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button
    private lateinit var buttonReadLatestImage: Button
    private lateinit var imageView: ImageView

    private var latestImage: Uri? = null

    private val launcherPickPhotoFromCamera = registerForActivityResult(PickImageFromCameraContract) {
        latestImage = it
    }

    private val launcherPickPhotoFromGallery = registerForActivityResult(PickImageFromGalleryContract) {
        it?.let {
            latestImage = it
            showToast(it.path!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonReadLatestImage = findViewById(R.id.button_read_latest_image)
        imageView = findViewById(R.id.image_view)
        buttonCamera.setOnClickListener {
            launcherPickPhotoFromCamera.launch(null)
        }
        buttonReadLatestImage.setOnClickListener {
            contentResolver.delete(latestImage!!, null, null)
        }
    }

}
