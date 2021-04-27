package com.lomovskiy.lib.imagepicker

import android.app.Activity
import android.app.usage.ExternalStorageStats
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.util.*

object PickImageFromCameraContract : ActivityResultContract<Void?, Uri?>() {

    private var fileUri: Uri? = null

    override fun createIntent(context: Context, input: Void?): Intent {
        fileUri = createPhotoUri(context)
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

    private fun createPhotoUri(context: Context): Uri {
        val file = File("${ContextCompat.getExternalFilesDirs(context, )}/${UUID.randomUUID()}.jpg")
        return FileProvider.getUriForFile(context, "${context.packageName}.imagepicker.fileprovider", file)
    }

}