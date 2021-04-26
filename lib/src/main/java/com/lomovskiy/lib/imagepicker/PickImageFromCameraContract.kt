package com.lomovskiy.lib.imagepicker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract

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