pluginManagement {
    repositories {
        gradlePluginPortal()    // Официальный репозиторий Gradle для плагинов
        mavenCentral()          // Центральный репозиторий Maven
        google()                // Репозиторий Google (нужен для Android и некоторых Kotlin плагинов)
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "xMySample"

include("xUtils")
include("xRayProto")
include("xLogging")

include("xAppTest")
