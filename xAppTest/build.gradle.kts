plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
    application
}

application {
    mainClass.set("x.MainKt")
}

val jdkVersion = libs.versions.jdk.get().toInt()

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(jdkVersion)
}

dependencies {
    implementation(project(":xConfig"))
    implementation(project(":xLogging"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlin.stdlib)
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

val fatJar = tasks.register<Jar>("fatJar") {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from(
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    )
}

tasks.named("build") {
    dependsOn(fatJar)
}

ktlint {
    android.set(false)
    ignoreFailures.set(true)
}
