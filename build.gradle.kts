@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.minigdx.jvm)
    id("jacoco")
}

dependencies {
    implementation(libs.jackson)
    testImplementation(libs.bundles.test)
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
