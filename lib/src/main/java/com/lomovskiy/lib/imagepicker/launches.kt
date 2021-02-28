package com.lomovskiy.lib.imagepicker

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

inline fun ActivityResultLauncher<String>.launchForImage() {
    launch("image/*")
}

inline fun ActivityResultCaller.getGalleryImageLauncher(callback: ActivityResultCallback<Uri>): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.GetContent(), callback)
}
