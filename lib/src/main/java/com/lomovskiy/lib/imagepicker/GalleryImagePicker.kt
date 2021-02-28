package com.lomovskiy.lib.imagepicker

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class GalleryImagePicker(

) : ActivityResultCallback<Uri> {

    companion object {
        val contract = ActivityResultContracts.GetContent()
        val contentType = "image/*"
    }

    override fun onActivityResult(result: Uri) {

    }

    fun createLauncher(caller: ActivityResultCaller): ActivityResultLauncher<String> {
        return caller.registerForActivityResult(contract, this)
    }

}
