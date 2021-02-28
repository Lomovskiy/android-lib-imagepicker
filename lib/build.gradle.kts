import com.lomovskiy.lib.imagepicker.buildsrc.Config

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
}

android {

    defaultConfig {
        minSdkVersion(Config.Versions.Android.min)
        targetSdkVersion(Config.Versions.Android.target)
        compileSdkVersion(Config.Versions.Android.compile)
        buildToolsVersion(Config.Versions.buildTools)
        versionCode(Config.Versions.code)
        versionName(Config.Versions.name)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(com.lomovskiy.lib.imagepicker.buildsrc.Config.Deps.kotlinStd)
    implementation(com.lomovskiy.lib.imagepicker.buildsrc.Config.Deps.ui)

}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

artifacts {
    archives(sourcesJar)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = com.lomovskiy.lib.imagepicker.buildsrc.Config.Publish.groupId
                artifactId = com.lomovskiy.lib.imagepicker.buildsrc.Config.Publish.artifactId
                version = Config.Versions.name
            }
        }
    }
}
