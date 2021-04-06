package com.lomovskiy.lib.imagepicker.sample

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lomovskiy.lib.imagepicker.PickImageFromCameraContract
import com.lomovskiy.lib.imagepicker.PickImageFromGalleryContract
import com.lomovskiy.lib.ui.showToast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button

    private var uri: Uri? = null

    private val galleryLauncher = registerForActivityResult(PickImageFromGalleryContract) {
        lifecycleScope.launch {
            if (it != null) {
                val file = AppLoader.imagePicker.handleResult(it)
                showToast(file.absolutePath)
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(PickImageFromCameraContract) {
        lifecycleScope.launch {
            if (it != null) {
                val file = AppLoader.imagePicker.handleResult(it)
                showToast(file.absolutePath)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonCamera.setOnClickListener {
            cameraLauncher.launch(AppLoader.imagePicker.getNewPhotoUri())
        }
        buttonGallery.setOnClickListener {
            galleryLauncher.launch(null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
