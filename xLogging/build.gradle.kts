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
    api(libs.kotlin.logging)
    api(libs.logback.classic)
    api(libs.logstash.logback.encoder)
}

ktlint {
    android.set(false)
    ignoreFailures.set(true)
}
