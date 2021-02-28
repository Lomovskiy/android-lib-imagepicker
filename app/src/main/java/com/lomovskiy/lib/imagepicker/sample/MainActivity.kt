package com.lomovskiy.lib.imagepicker.sample

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.lomovskiy.lib.imagepicker.GalleryImagePicker
import com.lomovskiy.lib.imagepicker.ImagePicker
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity() {

    private val imagePicker: ImagePicker = GalleryImagePicker()

    private val getFromGalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent(), imagePicker)

    private val getFromCameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        contentResolver.delete(uri!!, null)
    }

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonCamera.setOnClickListener {
            val file = File("${cacheDir.path}${File.separator}${UUID.randomUUID()}.jpg${File.separator}")
            uri = FileProvider.getUriForFile(
                this,
                "${packageName}.imagepicker.fileprovider",
                file
            )
            getFromCameraLauncher.launch(uri)
        }
        buttonGallery.setOnClickListener {
            imagePicker.deliveryTo(getFromGalleryLauncher)
        }
    }

}
