package com.adrienben.tools.gltf.validation

import com.adrienben.tools.gltf.models.GltfAsset
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class ExtensionsTest {

    @Test
    fun itCanReadExtensions() {
        val path = File(ExtensionsTest::class.java.getResource("/extensions/lights.gltf").toURI()).absolutePath
        val asset = GltfAsset.fromFile(path)
        assertEquals(4, asset?.extensions?.lightsPunctual?.size)
    }

    @Test
    fun itCanReadExtensionsWithGlb() {
        val path = File(ExtensionsTest::class.java.getResource("/extensions/lights.glb").toURI()).absolutePath
        val asset = GltfAsset.fromFile(path)
        assertEquals(4, asset?.extensions?.lightsPunctual?.size)
    }

    @Test
    fun itCanReadFileWithoutExtensions() {
        val path = File(ExtensionsTest::class.java.getResource("/extensions/cube_translated.gltf").toURI()).absolutePath
        val asset = GltfAsset.fromFile(path)
        assertEquals(null, asset?.extensions)
    }
}
