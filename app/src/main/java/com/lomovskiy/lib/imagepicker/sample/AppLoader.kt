package com.lomovskiy.lib.imagepicker.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.lomovskiy.lib.imagepicker.ImageCompressor
import com.lomovskiy.lib.imagepicker.ImageCompressorImpl
import com.lomovskiy.lib.imagepicker.ImagePicker
import com.lomovskiy.lib.imagepicker.ImagePickerImpl

class AppLoader : Application() {

    companion object {
        lateinit var imageCompressor: ImageCompressor
        lateinit var imagePicker: ImagePicker
    }

    override fun onCreate() {
        super.onCreate()
        imageCompressor = ImageCompressorImpl(this, 1600, 1200, 7 * 1024)
        imagePicker = ImagePickerImpl(this, filesDir.path, imageCompressor)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
