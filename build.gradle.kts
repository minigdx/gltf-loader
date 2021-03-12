plugins {
    id("com.github.minigdx.gradle.plugin.developer.jvm") version "1.0.0-alpha1"
    id("jacoco")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // JsonObject can be exposed through deserialization
    api("com.beust:klaxon:5.2")

    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
}
