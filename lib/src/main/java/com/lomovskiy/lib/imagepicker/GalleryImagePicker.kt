package com.lomovskiy.lib.imagepicker

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher

class GalleryImagePicker(

) : ImagePicker, ActivityResultCallback<Uri> {

    override fun deliveryTo(launcher: ActivityResultLauncher<String>) {
        launcher.launch("image/*")
    }

    override fun onActivityResult(result: Uri) {

    }

}
