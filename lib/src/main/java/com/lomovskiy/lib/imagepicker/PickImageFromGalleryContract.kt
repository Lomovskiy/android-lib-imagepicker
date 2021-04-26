package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

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

