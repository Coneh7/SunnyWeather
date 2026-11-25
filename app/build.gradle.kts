plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sunnyweather"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.sunnyweather"
        minSdk = 30
        targetSdk = 36
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
}

dependencies {
        // RecyclerView 列表控件
        implementation("androidx.recyclerview:recyclerview:1.3.2")
        // Lifecycle + ViewModel + LiveData（替代废弃的 lifecycle-extensions）
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

        // Material 设计组件
        implementation("com.google.android.material:material:1.12.0")
        // Retrofit 网络请求框架
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")
        // Kotlin 协程
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}