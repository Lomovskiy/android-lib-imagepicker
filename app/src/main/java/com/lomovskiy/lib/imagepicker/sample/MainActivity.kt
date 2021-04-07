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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
    }

}
