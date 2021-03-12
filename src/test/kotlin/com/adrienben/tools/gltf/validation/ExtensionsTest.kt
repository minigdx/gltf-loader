package com.adrienben.tools.gltf.validation

import com.adrienben.tools.gltf.models.GltfAsset
import com.beust.klaxon.JsonObject
import java.io.File
import junit.framework.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

    @Test
    fun itCanReadExtensions() {
        val path = File(ExtensionsTest::class.java.getResource("/extensions/lights.gltf").toURI()).absolutePath
        val asset = GltfAsset.fromFile(path)
        assertEquals(1, asset?.extensions?.size)
        val extension = asset?.extensions?.values?.first() as JsonObject
        assertTrue(extension.containsKey("lights"))
    }

    @Test
    fun itCanReadFileWithoutExtensions() {
        val path = File(ExtensionsTest::class.java.getResource("/extensions/cube_translated.gltf").toURI()).absolutePath
        val asset = GltfAsset.fromFile(path)!!
        assertTrue(asset.extensions.isEmpty())
    }
}
