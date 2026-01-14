plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
}

val jdkVersion = libs.versions.jdk.get().toInt()

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(jdkVersion)
}

dependencies {
    implementation(libs.kotlin.stdlib)
}

ktlint {
    android.set(false)
    ignoreFailures.set(true)
}
