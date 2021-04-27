package com.lomovskiy.lib.imagepicker.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.lomovskiy.lib.imagepicker.ImageCompressor
import com.lomovskiy.lib.imagepicker.ImageCompressorImpl

class AppLoader : Application() {

    companion object {

        lateinit var imageCompressor: ImageCompressor

    }

    override fun onCreate() {
        super.onCreate()
        imageCompressor = ImageCompressorImpl(this, 1600, 1200, 1024)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
