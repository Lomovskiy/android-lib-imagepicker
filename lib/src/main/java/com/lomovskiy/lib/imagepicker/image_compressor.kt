package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.core.content.FileProvider
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.*
import io.reactivex.Completable
import kotlinx.coroutines.rx2.rxCompletable
import java.io.File
import java.util.*
import kotlin.math.max

interface ImageCompressor {

//    suspend fun compressToFile(source: File, dest: File)
//
//    suspend fun compressToFile(source: Uri, dest: File)

    suspend fun compressToBase64(source: File): String

    suspend fun compressToBase64(source: Uri): String

    fun getNewPhotoUri(): Uri

}

class ImageCompressorImpl(
    private val context: Context,
    private val maxWidth: Int,
    private val maxHeight: Int,
    private val maxSize: Long
) : ImageCompressor {

    private val authority: String = "${context.packageName}.imagepicker.fileprovider"

//    override suspend fun compressToFile(source: File, dest: File) {
//        Compressor.compress(context, source) {
//            default(width = maxWidth, height = maxHeight)
//            size(maxSize)
//            destination(dest)
//        }
//    }
//
//    override suspend fun compressToFile(source: Uri, dest: File) {
//        val tmpFile = getNewTempFile()
//        tmpFile.writeBytes(context.contentResolver.openInputStream(source)!!.readBytes())
//        compressToFile(tmpFile, dest)
//        tmpFile.delete()
//    }

    override suspend fun compressToBase64(source: File): String {
        return compressToBase64(source, 100)
    }

    override suspend fun compressToBase64(source: Uri): String {
        val tmpFile = getNewTempFile()
        tmpFile.writeBytes(context.contentResolver.openInputStream(source)!!.readBytes())
        val result = compressToBase64(tmpFile)
        return result
    }

    override fun getNewPhotoUri(): Uri {
        return FileProvider.getUriForFile(context, authority, getNewTempFile())
    }

    private suspend fun compressToBase64(source: File, quality: Int): String {
        val compressed = Compressor.compress(context, source) {
            default(maxWidth, maxHeight, quality = quality)
        }
        val str = Base64.encodeToString(compressed.readBytes(), Base64.DEFAULT)
        if (maxSize < str.length) {
            return compressToBase64(source, quality - 10)
        }
        context.cacheDir.deleteRecursively()
        return str
    }

    private fun getNewTempFile(): File {
        return File("${context.cacheDir}/${UUID.randomUUID()}.jpg")
    }

    private fun getNewPhotoFile(): File {
        return File("${context.filesDir}/${UUID.randomUUID()}.jpg")
    }

}
