import java.util.*

plugins {
    kotlin("jvm") version "1.3.70"
    id("maven-publish")
    id("jacoco")
    id("com.jfrog.bintray") version "1.8.5"
}

group = "com.adrienben.tools"
version = project.properties["version"] ?: "1.0.5-SNAPSHOT"

if (version == "unspecified") {
    version = "1.0.5-SNAPSHOT"
}

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


val properties = Properties()
if (project.file("local.properties").exists()) {
    properties.load(project.file("local.properties").inputStream())
}


val bintrayUser = if (project.hasProperty("bintray_user")) {
    project.property("bintray_user") as? String
} else {
    System.getProperty("BINTRAY_USER")
}

val bintrayKey = if (project.hasProperty("bintray_key")) {
    project.property("bintray_key") as? String
} else {
    System.getProperty("BINTRAY_KEY")
}

configure<com.jfrog.bintray.gradle.BintrayExtension> {
    user = properties.getProperty("bintray.user") ?: bintrayUser
    key = properties.getProperty("bintray.key") ?: bintrayKey
    publish = true
    setPublications("maven")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "minigdx"
        name = project.name
        githubRepo = "dwursteisen/gltf-loader.git"
        vcsUrl = "https://github.com/dwursteisen/gltf-loader.git"
        description = project.description
        setLabels("java")
        setLicenses("MIT")
        desc = description
        version(closureOf<com.jfrog.bintray.gradle.BintrayExtension.VersionConfig> {
            this.name = project.version.toString()
            released = Date().toString()
        })
    })
}
