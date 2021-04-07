package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.util.Base64
import id.zelory.compressor.Compressor
import io.reactivex.Single
import kotlinx.coroutines.rx2.rxSingle
import java.io.File
import java.util.*

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

    private val compressorInt = Compressor(context).apply {
        setMaxWidth(maxWidth)
        setMaxHeight(maxHeight)
    }

    override suspend fun compressToBase64(source: File): String {
        return compressToBase64(source, 100)
    }

    override fun compressToBase64Single(source: File): Single<String> {
        return rxSingle {
            return@rxSingle compressToBase64(source)
        }
    }

    private fun compressToBase64(source: File, quality: Int): String {
        compressorInt.setQuality(quality)
        val compressed = compressorInt.compressToFile(source)
        val str = Base64.encodeToString(compressed.readBytes(), Base64.DEFAULT)
        if (maxSize < str.length) {
            return compressToBase64(source, quality - 10)
        }
        return str
    }

}
