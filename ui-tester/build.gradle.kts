import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}

group = "me.admin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.appium:java-client:7.5.0")
    implementation("io.github.testsigma-eng:appium-flutterfinder-java:0.1.1")
    testImplementation(kotlin("test-junit"))
    testImplementation("com.github.appium:java-client:7.5.0")
    testImplementation("io.github.testsigma-eng:appium-flutterfinder-java:0.0.4")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}