package com.adrienben.tools.gltf.validation

import com.adrienben.tools.gltf.models.GltfAsset
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class MapperTest {

    @Test
    fun itCanMapCustomProperties() {
        val path = File(ExtensionsTest::class.java.getResource("/cube_with_custom_properties.gltf").toURI()).absolutePath
        val extras = GltfAsset.fromFile(path).nodes.first().extras
        assertEquals("example of value", extras?.get("name"))
    }
}
