

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version GradlePlugins.Kotlin.version
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintPlugin
}

android {
    compileSdk = App.compileSdk
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.noahjutz.gymroutines"
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        versionCode = 43
        versionName = "0.1.0-beta11"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += "room.schemaLocation" to "$projectDir/schemas"
            }
        }

        sourceSets {
            getByName("androidTest").assets.srcDirs("$projectDir/schemas")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lint {
        textReport = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }

    packagingOptions {
        resources.excludes.addAll(
            listOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/*.kotlin_module"
            )
        )
    }
}

dependencies {
    implementation(Libs.Core.core)
    implementation(Libs.Core.splashScreen)

    implementation(Libs.Coroutines.android)

    implementation(Libs.Material.material)

    implementation(Libs.Serialization.json)

    implementation(Libs.Room.room)
    kapt(Libs.Room.compiler)
    implementation(Libs.Room.runtime)
    testImplementation(Libs.Room.testing)
    androidTestImplementation(Libs.Room.testing)

    kapt(Libs.Lifecycle.lifecycle)
    implementation(Libs.Lifecycle.livedata)
    implementation(Libs.Lifecycle.process)

    implementation(Libs.Navigation.compose)

    testImplementation(TestLibs.Junit4.junit4)
    testImplementation(TestLibs.AssertJ.assertJ)

    testImplementation(TestLibs.Mockk.unit)
    androidTestImplementation(TestLibs.Mockk.instrumented)

    implementation(Libs.Activity.compose)

    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.iconsCore)
    implementation(Libs.Compose.iconsExtended)
    implementation(Libs.Compose.runtimeLivedata)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.tooling)
    androidTestImplementation(Libs.Compose.test)
    androidTestImplementation(Libs.Compose.testJunit4)

    implementation(Libs.Koin.android)
    implementation(Libs.Koin.compose)
    testImplementation(Libs.Koin.test)

    implementation(Libs.DataStore.preferences)

    implementation(Libs.ProcessPhoenix.processPhoenix)

    androidTestImplementation(TestLibs.Test.core)
    androidTestImplementation(TestLibs.Test.coreKtx)

    implementation(Libs.Accompanist.navigationAnimation)
    implementation(Libs.Accompanist.navigationMaterial)
    implementation(Libs.Accompanist.placeholder)
}

ktlint {
    android.set(true)
    ignoreFailures.set(true)
    disabledRules.add("no-wildcard-imports")
}
