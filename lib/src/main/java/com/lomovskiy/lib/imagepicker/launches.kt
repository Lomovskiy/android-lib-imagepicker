package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

private val galleryIntent: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {
    addCategory(Intent.CATEGORY_OPENABLE)
    type = "image/*"
}

object PickImageFromGallery : ActivityResultContract<Void, Uri?>() {

    override fun createIntent(context: Context, input: Void?): Intent {
        return galleryIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent?.data
    }

}
