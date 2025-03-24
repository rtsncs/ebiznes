val ktor_version: String by project

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    kotlin("plugin.serialization").version("2.1.0")
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}

application {
    mainClass = "org.example.AppKt"
}
