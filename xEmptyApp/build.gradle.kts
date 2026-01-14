plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

application {
    println("-Dproject.dir=${project.projectDir}")
    println("-Dproject.path=${project.path}")
    // передаём в JVM директорию и имя подпроекта
    applicationDefaultJvmArgs = listOf(
        "-Dproject.dir=${project.projectDir}",
        "-Dproject.name=${project.name}"
    )
    mainClass.set("MainKt")
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