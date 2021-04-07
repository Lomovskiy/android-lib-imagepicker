package com.lomovskiy.lib.imagepicker.sample

import android.Manifest
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lomovskiy.lib.imagepicker.ImageCompressor
import com.lomovskiy.lib.imagepicker.PickImageFromCameraContract
import com.lomovskiy.lib.imagepicker.PickImageFromGalleryContract
import com.lomovskiy.lib.ui.showToast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button

    private val imageCompressor: ImageCompressor = AppLoader.imageCompressor

    private val permissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

    }

    private val galleryLauncher = registerForActivityResult(PickImageFromGalleryContract) {
        lifecycleScope.launch {
            if (it != null) {
                val str = imageCompressor.compressToBase64(it)
                showToast(str.length.toString())
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(PickImageFromCameraContract) {
        lifecycleScope.launch {
            if (it != null) {
                val str = imageCompressor.compressToBase64(it)
                showToast(str.length.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionsLauncher.launch(arrayOf(
                Manifest.permission.CAMERA
        ))
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonCamera.setOnClickListener {
            cameraLauncher.launch(imageCompressor.getNewPhotoUri())
        }
        buttonGallery.setOnClickListener {
            galleryLauncher.launch(null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
