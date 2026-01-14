plugins {
    alias(libs.plugins.kotlin.jvm)
}

val jdkVersion = libs.versions.jdk.get().toInt()

group = "x.project"
version = "unspecified"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jdkVersion))
    }
}

kotlin {
    jvmToolchain(jdkVersion)
}

dependencies {
    implementation(libs.kotlin.stdlib)
}
