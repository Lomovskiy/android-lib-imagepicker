package com.lomovskiy.lib.imagepicker.buildsrc

object Config {

    object Publish {
        const val groupId = "com.lomovskiy.lib"
        const val artifactId = "image-picker"
    }

    object GradlePlugins {

        const val android = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    }

    object Versions {

        const val code = 4
        const val name = "1.0.3"

        object Android {

            const val target = 30
            const val compile = 30
            const val min = 16

        }

        const val androidGradlePlugin = "4.1.2"
        const val buildTools = "30.0.3"

        const val kotlin = "1.4.30"
        const val kotlinCoroutinesRx2: String = "1.4.3"

        const val ui = "1.0.8"
        const val rxJava: String = "2.2.9"
        const val compressor: String = "2.1.1"

    }

    object Modules {

        const val app = ":app"
        const val lib = ":lib"

    }

    object Deps {

        const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlinCoroutinesRx2: String = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.kotlinCoroutinesRx2}"
        const val ui = "com.github.lomovskiy:android-lib-ui:${Versions.ui}"
        const val rxJava: String = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val compressor: String = "id.zelory:compressor:${Versions.compressor}"

    }

}
