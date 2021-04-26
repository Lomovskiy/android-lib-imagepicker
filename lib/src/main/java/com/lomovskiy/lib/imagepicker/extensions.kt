package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.util.*

fun createPhotoUri(context: Context, ext: String): Uri {
    val file = File("${ContextCompat.getNoBackupFilesDir(context)}/${UUID.randomUUID()}$ext")
    val uri: Uri = FileProvider.getUriForFile(context, "${context.packageName}.imagepicker.fileprovider", file)
    return uri
}

private fun grantUriPermission(context: Context, intent: Intent, uri: Uri) {
    val resolveInfo: ResolveInfo? = context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
    if (resolveInfo != null) {
        context.grantUriPermission(resolveInfo.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    }
}

private fun revokeUriPermission(context: Context, uri: Uri) {
    context.revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
}