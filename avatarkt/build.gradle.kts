plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.mavenPublish)
}

group = "io.github.feliperce"
version = "0.3.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AvatarKt"
            isStatic = true
        }
    }

    jvm()

    js {
        browser()
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(libs.compose.uiToolingPreview)
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.testJunit)
                implementation(libs.junit)
                implementation(libs.compose.ui.test.junit4)
                implementation(libs.kotlinx.coroutinesSwing)

                val osName = System.getProperty("os.name").lowercase()
                val osArch = System.getProperty("os.arch").lowercase()
                val targetOs = when {
                    osName.contains("win") -> "windows"
                    osName.contains("mac") || osName.contains("darwin") -> "macos"
                    else -> "linux"
                }
                val targetArch = when {
                    osArch.contains("aarch64") || osArch.contains("arm64") -> "arm64"
                    else -> "x64"
                }
                implementation("org.jetbrains.skiko:skiko-awt-runtime-$targetOs-$targetArch:0.9.37.4")
            }
        }
    }
}

android {
    namespace = "io.github.feliperce.avatarkt"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates(group.toString(), "avatarkt", version.toString())

    pom {
        name.set("Avatar KT")
        description.set("Kotlin Multiplatform library for generating unique, deterministic avatars using Compose Canvas.")
        url.set("https://github.com/feliperce/avatar-kt")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("feliperce")
                name.set("Felipe Celestino")
                url.set("https://github.com/feliperce")
            }
        }

        scm {
            url.set("https://github.com/feliperce/avatar-kt")
            connection.set("scm:git:git://github.com/feliperce/avatar-kt.git")
            developerConnection.set("scm:git:ssh://git@github.com/feliperce/avatar-kt.git")
        }
    }
}

tasks.named<Test>("jvmTest") {
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        showStandardStreams = true
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
