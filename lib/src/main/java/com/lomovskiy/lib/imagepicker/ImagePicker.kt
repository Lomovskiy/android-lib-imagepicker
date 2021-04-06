package com.lomovskiy.lib.imagepicker

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import java.io.File


interface ImagePicker {

    enum class Type {
        GALLERY,
        CAMERA,
        IN_APP_CAMERA
    }

    interface ResultTarget {

        fun onImageSourceResult(type: Type, result: File)

    }

    fun pick(type: Type, fragment: Fragment, target: ResultTarget) {
        fragment.registerForActivityResult(ActivityResultContracts.GetContent()) {

        }
    }

}
