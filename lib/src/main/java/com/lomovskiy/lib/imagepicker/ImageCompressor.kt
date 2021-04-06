package com.lomovskiy.lib.imagepicker

import android.content.Context
import android.graphics.Bitmap
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.size
import io.reactivex.Completable
import kotlinx.coroutines.rx2.rxCompletable
import java.io.File

class ImageCompressor(private val context: Context) {

    suspend fun compress(imageFile: File,
                         width: Int = 612,
                         height: Int = 816,
                         format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
                         quality: Int = 80) {
        Compressor.compress(context, imageFile) {
            default(width, height, format, quality)
        }
    }

    suspend fun compress(imageFile: File,
                         width: Int = 612,
                         height: Int = 816,
                         format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
                         size: Long = 5 * 1024) {
        Compressor.compress(context, imageFile) {
            default(width, height, format)
            size(size)
        }
    }

    fun compress(imageFile: File,
                         width: Int = 612,
                         height: Int = 816,
                         format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
                         quality: Int = 80): Completable {
        rxCompletable {
            Compressor.compress(context, imageFile) {
                default(width, height, format, quality)
            }
        }
    }

    fun compress(imageFile: File,
                         width: Int = 612,
                         height: Int = 816,
                         format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG,
                         size: Long = 5 * 1024): Completable {
        rxCompletable {
            Compressor.compress(context, imageFile) {
                default(width, height, format)
                size(size)
            }
        }
    }

}