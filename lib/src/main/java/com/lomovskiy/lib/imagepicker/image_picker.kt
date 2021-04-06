package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import java.io.File
import java.util.*


interface ImagePicker {

    fun handleResultSingle(imageUri: Uri): Single<File>

    suspend fun handleResult(imageUri: Uri): File

}

class ImagePickerImpl(
    private val context: Context,
    private val destinationPath: String,
    private val compressor: ImageCompressor?
) : ImagePicker {

    override fun handleResultSingle(imageUri: Uri): Single<File> {
        return Single.fromCallable {
            val tmpFile = getNewTempFile()
            tmpFile.writeBytes(context.contentResolver.openInputStream(imageUri)!!.readBytes())
            val photoFile = getNewPhotoFile()
            if (compressor == null) {
                photoFile.writeBytes(tmpFile.readBytes())
            } else {
                compressor.compressCompletable(tmpFile, photoFile).blockingAwait()
            }
            return@fromCallable photoFile
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun handleResult(imageUri: Uri): File {
        val tmpFile = getNewTempFile()
        tmpFile.writeBytes(context.contentResolver.openInputStream(imageUri)!!.readBytes())
        val photoFile = getNewPhotoFile()
        if (compressor == null) {
            photoFile.writeBytes(tmpFile.readBytes())
        } else {
            compressor.compress(tmpFile, photoFile)
        }
        tmpFile.delete()
        context.cacheDir.deleteRecursively()
        return photoFile
    }

    private fun getNewTempFile(): File {
        return File("${context.cacheDir}/${UUID.randomUUID()}.jpg")
    }

    private fun getNewPhotoFile(): File {
        return File("${destinationPath}/${UUID.randomUUID()}.jpg")
    }

}
