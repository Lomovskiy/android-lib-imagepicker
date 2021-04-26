package com.lomovskiy.lib.imagepicker.sample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lomovskiy.lib.imagepicker.ImageCompressor
import com.lomovskiy.lib.imagepicker.PickImageFromCameraContract
import com.lomovskiy.lib.imagepicker.PickImageFromGalleryContract
import com.lomovskiy.lib.imagepicker.createPhotoUri
import com.lomovskiy.lib.ui.showToast
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button

    private val launcherPickPhotoFromCamera = registerForActivityResult(PickImageFromCameraContract) {
        it?.let {
            print(it)
        }
    }

    private val launcherPickPhotoFromGallery = registerForActivityResult(PickImageFromGalleryContract) {
        it?.let {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonCamera.setOnClickListener {
            launcherPickPhotoFromCamera.launch(createPhotoUri(this, ".jpg"))
        }
    }

}
