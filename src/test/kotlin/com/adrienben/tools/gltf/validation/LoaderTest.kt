package com.adrienben.tools.gltf.validation

import com.adrienben.tools.gltf.models.GltfAsset
import org.junit.Test
import java.io.File

class LoaderTest {

    @Test
    fun itCanReadExtensions() {
        val path = File(ExtensionsTest::class.java.getResource("/cube_with_texture.gltf").toURI()).absolutePath
        GltfAsset.fromFile(path)
    }

    @Test
    fun itCanReadExtensionsWithGlb() {
        val path = File(ExtensionsTest::class.java.getResource("/cube_with_texture.glb").toURI()).absolutePath
        GltfAsset.fromFile(path)
    }
}
