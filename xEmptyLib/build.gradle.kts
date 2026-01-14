plugins {
    alias(libs.plugins.kotlin.jvm)
}

val jdkVersion = libs.versions.jdk.get().toInt()

group = "x"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(jdkVersion)
}

dependencies {
    implementation(project(":xLogback"))
    implementation(libs.kotlin.stdlib)
}