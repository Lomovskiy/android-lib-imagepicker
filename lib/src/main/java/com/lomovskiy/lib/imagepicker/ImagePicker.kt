package com.lomovskiy.lib.imagepicker

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher

interface ImagePicker : ActivityResultCallback<Uri> {

    enum class Type {
        GALLERY,
        CAMERA,
        IN_APP_CAMERA
    }

    interface ResultTarget {

        fun onImageSourceResult(type: Type, result: Result<Uri>)

    }

    fun deliveryTo(launcher: ActivityResultLauncher<String>)

}
