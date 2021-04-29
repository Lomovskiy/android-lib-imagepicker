package com.lomovskiy.lib.imagepicker.sample

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.lomovskiy.lib.imagepicker.ImageCompressorImpl
import com.lomovskiy.lib.imagepicker.PickImageFromCameraContract
import com.lomovskiy.lib.imagepicker.PickImageFromGalleryContract
import com.lomovskiy.lib.ui.showToast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button
    private lateinit var buttonReadLatestImage: Button
    private lateinit var imageView: ImageView

    private var latestImage: Uri? = null

    private val launcherPickPhotoFromCamera = registerForActivityResult(PickImageFromCameraContract) {
        latestImage = it
    }

    private val launcherPickPhotoFromGallery = registerForActivityResult(PickImageFromGalleryContract) {
        latestImage = it
    }

    private val launcherPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCamera = findViewById(R.id.button_camera)
        buttonGallery = findViewById(R.id.button_gallery)
        buttonReadLatestImage = findViewById(R.id.button_read_latest_image)
        imageView = findViewById(R.id.image_view)
        launcherPermissions.launch(arrayOf(android.Manifest.permission.CAMERA))
        buttonCamera.setOnClickListener {
            launcherPickPhotoFromCamera.launch(null)
        }
        buttonGallery.setOnClickListener {
            launcherPickPhotoFromGallery.launch(null)
        }
        buttonReadLatestImage.setOnClickListener {
            lifecycleScope.launch {
                val file = File("${ContextCompat.getNoBackupFilesDir(this@MainActivity)}/${UUID.randomUUID()}.jpg")
                file.writeBytes(contentResolver.openInputStream(latestImage!!)!!.readBytes())
                val str = AppLoader.imageCompressor.compressToBase64(file)
                file.delete()
                showToast(str.length.toString())
            }
        }
    }

}
