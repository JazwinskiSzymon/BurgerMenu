plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.podejscie1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.podejscie1"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.preference.ktx)
    val room_version = "2.7.0"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:$room_version")

    // retrofit

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // coroutine

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("com.google.android.material:material:1.12.0")
    // ViewModel i LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1") 
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    implementation ("org.osmdroid:osmdroid-android:6.1.18")


    implementation ("androidx.fragment:fragment-ktx:1.4.0")
    implementation ("androidx.activity:activity-ktx:1.6.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}