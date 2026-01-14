plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

application {
    mainClass.set("x.MainKt")
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
    implementation(project(":xLogging"))
    implementation(project(":xUtils"))
    implementation(libs.kotlin.stdlib)

//    implementation(libs.dotenv.kotlin)

    implementation(libs.hoplite.core)
    implementation(libs.hoplite.yaml)
}