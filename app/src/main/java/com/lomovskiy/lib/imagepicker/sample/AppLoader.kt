package com.lomovskiy.lib.imagepicker.sample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class AppLoader : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
