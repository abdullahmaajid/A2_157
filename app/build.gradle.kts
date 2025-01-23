plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

android {


    namespace = "com.example.projectakhir"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.projectakhir"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    viewBinding {
        enable = true // Gunakan `isEnabled` alih-alih `enabled`
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2") // Versi terbaru RecyclerView
    implementation ("androidx.appcompat:appcompat:1.6.1") // Versi terbaru AppCompat


    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.android.material:material:1.9.0")

    implementation ("androidx.compose.ui:ui:1.5.4")
    implementation ("androidx.compose.material:material:1.5.4")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation ("androidx.navigation:navigation-compose:2.7.4" )// Navigation Compose)
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation ("androidx.compose.material:material-icons-core:1.5.4")
    implementation ("androidx.compose.material:material-icons-extended:1.5.4")



    // LiveData dan ViewModel
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Jika Anda menggunakan Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Jika Anda menggunakan Kotlin Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation ("androidx.compose.runtime:runtime-livedata:1.0.0")

    implementation ("androidx.compose.material3:material3:1.1.0") // Pastikan versi terbaru
    implementation ("androidx.compose.ui:ui:1.4.0") // Pastikan versi terbaru

// Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

// UI
    implementation("io.coil-kt:coil-compose:2.1.0")

// ViewModel dan Navigation
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation("androidx.navigation:navigation-compose:2.7.0")


    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

}