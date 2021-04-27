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

    suspend fun compressToBase64(source: File): String

}

class ImageCompressorImpl(
    private val context: Context,
    private val maxWidth: Int,
    private val maxHeight: Int,
    private val maxSize: Long,
    private val downgradeQualityStep: Int = 10
) : ImageCompressor {

    override suspend fun compressToBase64(source: File): String {
        return compressToBase64(source, 100)
    }

    private suspend fun compressToBase64(source: File, quality: Int): String {
        val compressed = Compressor.compress(context, source) {
            default(maxWidth, maxHeight, quality = quality)
        }
        val str = Base64.encodeToString(compressed.readBytes(), Base64.DEFAULT)
        compressed.delete()
        if (str.length <= maxSize || quality - downgradeQualityStep < 0) {
            return str
        }
        return compressToBase64(source, quality - downgradeQualityStep)
    }

}
