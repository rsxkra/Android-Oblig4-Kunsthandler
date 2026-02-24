plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.room")



}

android {
    namespace = "com.example.artshop"
    compileSdk = 35

    packaging {
        resources {
            pickFirsts += "META-INF/INDEX.LIST"
            pickFirsts += "META-INF/DEPENDENCIES"
            pickFirsts += "META-INF/io.netty.versions.properties"
        }
    }


    defaultConfig {
        applicationId = "com.example.artshop"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.litert.support.api)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.testing)
    implementation(libs.firebase.appdistribution.gradle)
    implementation(libs.androidx.room.common.jvm)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Retrofit
    implementation(libs.retrofit)

    // Kotlin Serialization Converter
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // OkHttp (Retrofit's HTTP client) - you might already have this
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}