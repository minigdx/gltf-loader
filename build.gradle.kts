plugins {
    kotlin("jvm") version "1.3.70"
    id("maven-publish")
    id("jacoco")
}

group = "com.adrienben.tools"
version = "1.0.5-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.beust:klaxon:5.2")

    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
