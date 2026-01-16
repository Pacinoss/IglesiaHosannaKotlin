plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.example.iglesiahosanna"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.iglesiahosanna"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
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
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment)


    // Dependencias para el carrusel y las tarjetas
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.ui.text)
    // implementation ("com.google.firebase:firebase-messaging")
  //  implementation ("com.google.firebase:firebase-analytics")
    //
   // implementation(libs.firebase.messaging.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // ... otras dependencias del proyecto ...
    // Retrofit para las llamadas a la API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Convertidor Gson para serializar/deserializar JSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //coonvierte el url en imagenes es coil
    implementation("io.coil-kt:coil:2.5.0")
    // Interceptor para ver las trazas de las llamadas (¡muy importante!)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // Para usar lifecycleScope, la forma moderna de gestionar corrutinas
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    /////////////////Dependecias de google email de firestore/////////////////////
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:34.7.0"))
    //the dependency for the Cloud Firestore library(dependencia para firestore cluoud)
    implementation("com.google.firebase:firebase-firestore")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")

}