plugins {
    alias(libs.plugins.kotlin.jvm)
}

val jdkVersion = libs.versions.jdk.get().toInt()

group = "x.project"
version = "unspecified"

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
    api(libs.dotenv.kotlin)
}