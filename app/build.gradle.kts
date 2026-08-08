import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)

}

val localProperties = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) {
        load(f.inputStream())
    }
}

android {
    namespace = "com.example.gestion_de_ervicios_turisticos"

    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.gestion_de_servicios_turisticos"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "MAPBOX_PUBLIC_TOKEN",
            "\"${localProperties.getProperty("MAPBOX_PUBLIC_TOKEN", "")}\""
        )
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

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)

    // Testing
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Material Icons
    implementation(libs.androidx.compose.material.icons.extended)

    // Google Authentication
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Mapbox
    implementation("com.mapbox.maps:android:11.26.0")
    implementation("com.mapbox.extension:maps-compose:11.26.0")

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    // ↑ "bom" = Bill of Materials: un paquete que asegura que TODAS
    // las librerías de Firebase que uses sean versiones compatibles entre sí.
    // Es una buena práctica: evita conflictos de versiones.

    implementation("com.google.firebase:firebase-auth-ktx")
    // ↑ Librería para login/registro de usuarios

    implementation("com.google.firebase:firebase-firestore-ktx")
    // ↑ Librería para guardar datos (base de datos)

    implementation("com.google.firebase:firebase-storage-ktx")

    implementation(libs.kotlinx.coroutines.play.services)
    // ↑ Librería para guardar imágenes
}