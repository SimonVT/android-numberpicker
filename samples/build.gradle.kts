import com.intermedia.numberpicker.Config

plugins {
    id("com.android.application")
}

android {
    compileSdkVersion(Config.Android.compileSdkVersion)
    buildToolsVersion(Config.Android.buildToolsVersion)

    defaultConfig {
        applicationId("com.intermedia.numberpicker.samples")
        minSdkVersion(Config.Android.minSdkVersion)
        targetSdkVersion(Config.Android.targetSdkVersion)
    }
}

dependencies {
    implementation(project(":library"))
}