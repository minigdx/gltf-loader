plugins {
    id("com.github.minigdx.gradle.plugin.developer.jvm") version "1.0.0-alpha0"
    id("jacoco")
}

dependencies {
    // JsonObject can be exposed through deserialization
    api("com.beust:klaxon:5.4")

    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.12")
}

minigdxDeveloper {
    this.name.set("gltf-loader")
    this.description.set("Loader for glTF2.0 files written in kotlin.")
    this.projectUrl.set("https://github.com/minigdx/gltf-loader")
    this.licence {
        name.set("MIT Licence")
        url.set("https://github.com/minigdx/gltf-loader/blob/develop/LICENSE")
    }
    developer {
        name.set("David Wursteisen")
        email.set("david.wursteisen+minigdx@gmail.com")
        url.set("https://github.com/dwursteisen")
    }

    developer {
        name.set("Adrien Bennadji")
        email.set("adrien.bennadji@live.fr")
        url.set("https://github.com/adrien-ben")
    }
}
