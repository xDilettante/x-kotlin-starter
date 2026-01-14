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
    implementation(libs.kotlin.logging)
    implementation(libs.dotenv.kotlin)
    implementation(libs.hoplite.core)
    implementation(libs.hoplite.yaml)

    implementation(project(":xUtils"))
}

ktlint {
    android.set(false)
    ignoreFailures.set(true)
}
