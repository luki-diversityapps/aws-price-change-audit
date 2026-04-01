plugins {
    kotlin("jvm") version "2.3.0"
    id("org.jlleitschuh.gradle.ktlint") version "14.0.1"
}

group = "io.github.lukidiversityapps"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("tools.jackson.core:jackson-databind:3.1.0")
    implementation("tools.jackson.module:jackson-module-kotlin:3.1.0")

    implementation(platform("software.amazon.awssdk:bom:2.25.50"))
    implementation("software.amazon.awssdk:sqs")
    implementation("software.amazon.awssdk:auth")
    implementation("software.amazon.awssdk:regions")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}
