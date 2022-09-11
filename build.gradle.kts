plugins {
    id("com.github.minigdx.gradle.plugin.developer.jvm") version "1.1.0"
    id("jacoco")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")

    testImplementation(kotlin("test-junit"))
    testImplementation("junit:junit:4.13.2")
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
