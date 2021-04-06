package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.graphics.Bitmap
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.*
import io.reactivex.Completable
import kotlinx.coroutines.rx2.rxCompletable
import java.io.File

interface ImageCompressor {

    suspend fun compress(source: File, dest: File)

    fun compressCompletable(source: File, dest: File): Completable

}

class ImageCompressorImpl(
    private val context: Context,
    private val width: Int,
    private val height: Int,
    private val size: Long
) : ImageCompressor {

    override suspend fun compress(
        source: File, dest: File
    ) {
        Compressor.compress(context, source) {
            resolution(width, height)
            format(Bitmap.CompressFormat.JPEG)
            size(size)
            quality(100)
            destination(dest)
        }
    }

    override fun compressCompletable(
        source: File, dest: File
    ): Completable {
        return rxCompletable { compress(source, dest) }
    }

}
