package com.lomovskiy.lib.imagepicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper
import java.io.File

object PickImageFromGalleryContract : ActivityResultContract<Void, Uri?>() {

    private val intent: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "image/*"
    }

    override fun createIntent(context: Context, input: Void?): Intent {
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent?.data
    }

}

object PickImageFromCameraContract : ActivityResultContract<Uri?, Uri?>() {

    private var fileUri: Uri? = null

    override fun createIntent(context: Context, input: Uri?): Intent {
        fileUri = input
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode == Activity.RESULT_OK) {
            return fileUri
        } else {
            return null
        }
    }

}
