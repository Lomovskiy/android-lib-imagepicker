package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.core.content.FileProvider
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.*
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.rx2.rxCompletable
import kotlinx.coroutines.rx2.rxSingle
import java.io.File
import java.util.*
import kotlin.math.max

interface ImageCompressor {

    suspend fun compressToBase64(source: File): String

    fun compressToBase64Single(source: File): Single<String>

}

class ImageCompressorImpl(
    private val context: Context,
    private val maxWidth: Int,
    private val maxHeight: Int,
    private val maxSize: Long
) : ImageCompressor {

    override suspend fun compressToBase64(source: File): String {
        return compressToBase64(source, 100)
    }

    override fun compressToBase64Single(source: File): Single<String> {
        return rxSingle {
            return@rxSingle compressToBase64(source)
        }
    }

    private suspend fun compressToBase64(source: File, quality: Int): String {
        val compressed = Compressor.compress(context, source) {
            default(maxWidth, maxHeight, quality = quality)
        }
        val str = Base64.encodeToString(compressed.readBytes(), Base64.DEFAULT)
        if (maxSize < str.length) {
            return compressToBase64(source, quality - 10)
        }
        return str
    }

}
