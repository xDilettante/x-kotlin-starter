import com.google.protobuf.gradle.*

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.protobuf)
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
    implementation(libs.bouncycastle.bcprov)
    implementation(libs.bouncycastle.bcpkix)
    implementation(libs.bouncycastle.bcutil)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.protobuf.kotlin)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.stub)
    implementation(libs.grpc.netty.shaded)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    plugins {
        id("grpc") {
            artifact = libs.protoc.gen.grpc.java.get().toString()
        }
        id("grpckt") {
            artifact = "${libs.protoc.gen.grpc.kotlin.get()}:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}
