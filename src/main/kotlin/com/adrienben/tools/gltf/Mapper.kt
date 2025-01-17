package com.adrienben.tools.gltf

import com.adrienben.tools.gltf.models.AccessorRaw
import com.adrienben.tools.gltf.models.AnimationRaw
import com.adrienben.tools.gltf.models.AnimationSamplerRaw
import com.adrienben.tools.gltf.models.AnimationTargetRaw
import com.adrienben.tools.gltf.models.AssetRaw
import com.adrienben.tools.gltf.models.BufferRaw
import com.adrienben.tools.gltf.models.BufferViewRaw
import com.adrienben.tools.gltf.models.CameraRaw
import com.adrienben.tools.gltf.models.ChannelRaw
import com.adrienben.tools.gltf.models.Extensions
import com.adrienben.tools.gltf.models.GltfAccessor
import com.adrienben.tools.gltf.models.GltfAlphaMode
import com.adrienben.tools.gltf.models.GltfAnimation
import com.adrienben.tools.gltf.models.GltfAnimationSampler
import com.adrienben.tools.gltf.models.GltfAnimationTarget
import com.adrienben.tools.gltf.models.GltfAnimationTargetPath
import com.adrienben.tools.gltf.models.GltfAsset
import com.adrienben.tools.gltf.models.GltfAssetRaw
import com.adrienben.tools.gltf.models.GltfBuffer
import com.adrienben.tools.gltf.models.GltfBufferTarget
import com.adrienben.tools.gltf.models.GltfBufferView
import com.adrienben.tools.gltf.models.GltfCamera
import com.adrienben.tools.gltf.models.GltfCameraType
import com.adrienben.tools.gltf.models.GltfChannel
import com.adrienben.tools.gltf.models.GltfColor
import com.adrienben.tools.gltf.models.GltfComponentType
import com.adrienben.tools.gltf.models.GltfExtension
import com.adrienben.tools.gltf.models.GltfFilter
import com.adrienben.tools.gltf.models.GltfImage
import com.adrienben.tools.gltf.models.GltfIndices
import com.adrienben.tools.gltf.models.GltfInterpolationType
import com.adrienben.tools.gltf.models.GltfLightPunctualExtension
import com.adrienben.tools.gltf.models.GltfMat4
import com.adrienben.tools.gltf.models.GltfMaterial
import com.adrienben.tools.gltf.models.GltfMesh
import com.adrienben.tools.gltf.models.GltfMetadata
import com.adrienben.tools.gltf.models.GltfMimeType
import com.adrienben.tools.gltf.models.GltfNode
import com.adrienben.tools.gltf.models.GltfNormalTextureInfo
import com.adrienben.tools.gltf.models.GltfOcclusionTextureInfo
import com.adrienben.tools.gltf.models.GltfOrthographic
import com.adrienben.tools.gltf.models.GltfPbrMetallicRoughness
import com.adrienben.tools.gltf.models.GltfPerspective
import com.adrienben.tools.gltf.models.GltfPrimitive
import com.adrienben.tools.gltf.models.GltfPrimitiveMode
import com.adrienben.tools.gltf.models.GltfQuaternion
import com.adrienben.tools.gltf.models.GltfRaw
import com.adrienben.tools.gltf.models.GltfSampler
import com.adrienben.tools.gltf.models.GltfScene
import com.adrienben.tools.gltf.models.GltfSkin
import com.adrienben.tools.gltf.models.GltfSparse
import com.adrienben.tools.gltf.models.GltfTexture
import com.adrienben.tools.gltf.models.GltfTextureInfo
import com.adrienben.tools.gltf.models.GltfType
import com.adrienben.tools.gltf.models.GltfValues
import com.adrienben.tools.gltf.models.GltfVec3
import com.adrienben.tools.gltf.models.GltfWrapMode
import com.adrienben.tools.gltf.models.ImageRaw
import com.adrienben.tools.gltf.models.IndicesRaw
import com.adrienben.tools.gltf.models.MaterialRaw
import com.adrienben.tools.gltf.models.MeshRaw
import com.adrienben.tools.gltf.models.NodeRaw
import com.adrienben.tools.gltf.models.NormalTextureInfoRaw
import com.adrienben.tools.gltf.models.OcclusionTextureInfoRaw
import com.adrienben.tools.gltf.models.OrthographicRaw
import com.adrienben.tools.gltf.models.PbrMetallicRoughnessRaw
import com.adrienben.tools.gltf.models.PerspectiveRaw
import com.adrienben.tools.gltf.models.PrimitiveRaw
import com.adrienben.tools.gltf.models.SamplerRaw
import com.adrienben.tools.gltf.models.SceneRaw
import com.adrienben.tools.gltf.models.SkinRaw
import com.adrienben.tools.gltf.models.SparseRaw
import com.adrienben.tools.gltf.models.TextureInfoRaw
import com.adrienben.tools.gltf.models.TextureRaw
import com.adrienben.tools.gltf.models.ValuesRaw
import java.lang.Math.PI

/**
 * [GltfAsset] mapper.
 *
 * On mapper must be created for each asset to map. Each call to
 * [Mapper.map] will generate a new mapped instance.
 */
internal class Mapper(private val gltfRaw: GltfRaw) {

    private val asset = gltfRaw.gltfAssetRaw

    private val buffers: List<GltfBuffer> by lazy {
        asset.buffers?.mapIndexed { index, buffer -> buffer.map(index, gltfRaw) } ?: emptyList()
    }

    private val bufferViews: List<GltfBufferView> by lazy {
        asset.bufferViews?.mapIndexed { index, view -> view.map(index, buffers) } ?: emptyList()
    }

    private val accessors: List<GltfAccessor> by lazy {
        asset.accessors?.mapIndexed { index, accessor -> accessor.map(index, bufferViews) } ?: emptyList()
    }

    private val samplers: List<GltfSampler> by lazy {
        asset.samplers?.mapIndexed { index, sampler -> sampler.map(index) } ?: emptyList()
    }

    private val images: List<GltfImage> by lazy {
        asset.images?.mapIndexed { index, image -> image.map(index, bufferViews) } ?: emptyList()
    }

    private val textures: List<GltfTexture> by lazy {
        asset.textures?.mapIndexed { index, texture -> texture.map(index, samplers, images) } ?: emptyList()
    }

    private val materials: List<GltfMaterial> by lazy {
        asset.materials?.mapIndexed { index, material -> material.map(index, textures) } ?: emptyList()
    }

    private val meshes: List<GltfMesh> by lazy {
        asset.meshes?.mapIndexed { index, mesh -> mesh.map(index, accessors, materials) } ?: emptyList()
    }

    private val cameras: List<GltfCamera> by lazy {
        asset.cameras?.mapIndexed { index, camera -> camera.map(index) } ?: emptyList()
    }

    private val nodes: List<GltfNode> by lazy {
        Array<GltfNode?>(asset.nodes?.size ?: 0) { null }.let {
            asset.nodes?.forEachIndexed { index, nodeRaw -> nodeRaw.map(index, asset, it, cameras, meshes) }
            it.requireNoNulls().asList()
        }
    }

    private val skins: List<GltfSkin> by lazy {
        asset.skins?.mapIndexed { index, skin -> skin.map(index, accessors, nodes) } ?: emptyList()
    }

    /**
     * Map the asset.
     */
    fun map(): GltfAsset {
        asset.nodes?.forEachIndexed { index, nodeRaw -> nodes[index].skin = nodeRaw.skin?.let { skins[it] } }

        val animations = asset.animations?.map { it.map(accessors, nodes) } ?: emptyList()
        val scenes = asset.scenes?.mapIndexed { index, scene -> scene.map(index, nodes) } ?: emptyList()

        return GltfAsset(
            asset = asset.asset.map(),
            extensionsUsed = asset.extensionsUsed?.toList(),
            extensionsRequired = asset.extensionsRequired?.toList(),
            extensions = asset.extensions?.toGltfExtensions(),
            buffers = buffers,
            bufferViews = bufferViews,
            accessors = accessors,
            samplers = samplers,
            images = images,
            textures = textures,
            materials = materials,
            meshes = meshes,
            cameras = cameras,
            nodes = nodes,
            skin = skins,
            animations = animations,
            scenes = scenes,
            scene = asset.scene?.let(scenes::get)
        )
    }
}

private const val KHR_LIGHTS_EXTENSION = "KHR_lights_punctual"
@Suppress("UNCHECKED_CAST")
private fun Extensions.toGltfExtensions(): GltfExtension {
    val lights = this[KHR_LIGHTS_EXTENSION] as? Map<String, Any> ?: emptyMap()
    val gltfLights = (lights["lights"] as? List<Map<String, Any>>)?.mapNotNull {
        val color = (it["color"] as List<Number>).let {
            val (r, g, b) = it
            GltfColor(r.toFloat(), g.toFloat(), b.toFloat(), 1.0f)
        }
        val type = it["type"].toString()
        val name = it["name"].toString()
        val intensity = (it["intensity"] as? Number)?.toFloat() ?: 1.0f
        when (type) {
            "point" -> GltfLightPunctualExtension.GltfPointLight(
                name = name,
                color = color,
                intensity = intensity
            )

            "directional" -> GltfLightPunctualExtension.GltfDirectionalLight(
                name = name,
                color = color,
                intensity = intensity
            )

            "spot" -> {
                val spot = it["spot"] as? Map<*, *>
                val innerConeAngle = spot?.get("innerConeAngle")?.toString()?.toFloat() ?: 0f
                val outerConeAngle = spot?.get("outerConeAngle")?.toString()?.toFloat() ?: (PI.toFloat() / 4.0f)
                GltfLightPunctualExtension.GltfSpotLight(
                    name = name,
                    color = color,
                    intensity = intensity,
                    innerConeAngle = innerConeAngle,
                    outerConeAngle = outerConeAngle
                )
            }

            else -> null
        }
    }

    return GltfExtension(
        lightsPunctual = gltfLights ?: emptyList(),
        unmapperExtensions = this.minus(KHR_LIGHTS_EXTENSION)
    )
}

private fun AssetRaw.map() = GltfMetadata(
    copyright,
    generator,
    version,
    minVersion
)

private fun BufferRaw.map(index: Int, gltfRaw: GltfRaw): GltfBuffer {
    return GltfBuffer(index, uri, byteLength, gltfRaw.data[index], name)
}

private fun BufferViewRaw.map(index: Int, buffers: List<GltfBuffer>) = GltfBufferView(
    index,
    buffers[buffer],
    byteOffset ?: 0,
    byteLength,
    byteStride,
    target?.let(GltfBufferTarget.Factory::fromCode),
    name
)

private fun IndicesRaw.map(bufferViews: List<GltfBufferView>) = GltfIndices(
    bufferViews[bufferView],
    byteOffset ?: 0,
    GltfComponentType.fromCode(componentType)
)

private fun ValuesRaw.map(bufferViews: List<GltfBufferView>) = GltfValues(
    bufferViews[bufferView],
    byteOffset
        ?: 0
)

private fun SparseRaw.map(bufferViews: List<GltfBufferView>) =
    GltfSparse(count, indices.map(bufferViews), values.map(bufferViews))

private fun AccessorRaw.map(index: Int, bufferViews: List<GltfBufferView>) = GltfAccessor(
    index,
    bufferView?.let(bufferViews::get),
    byteOffset ?: 0,
    GltfComponentType.fromCode(componentType),
    normalized ?: false,
    count,
    GltfType.fromCode(type),
    max?.map(Number::toFloat),
    min?.map(Number::toFloat),
    sparse?.map(bufferViews),
    name
)

private fun SamplerRaw.map(index: Int) = GltfSampler(
    index,
    magFilter?.let(GltfFilter.Factory::fromCode),
    minFilter?.let(GltfFilter.Factory::fromCode),
    wrapS?.let(GltfWrapMode.Factory::fromCode) ?: GltfWrapMode.REPEAT,
    wrapT?.let(GltfWrapMode.Factory::fromCode) ?: GltfWrapMode.REPEAT,
    name
)

private fun ImageRaw.map(index: Int, bufferViews: List<GltfBufferView>) = GltfImage(
    index,
    uri,
    uri?.decodeDataUri(),
    mimeType?.let(GltfMimeType.Factory::fromCode),
    bufferView?.let(bufferViews::get),
    name
)

private fun TextureRaw.map(index: Int, samplers: List<GltfSampler>, images: List<GltfImage>) = GltfTexture(
    index,
    sampler?.let(samplers::get) ?: GltfSampler(-1),
    source?.let(images::get),
    name
)

private fun TextureInfoRaw.map(textures: List<GltfTexture>) = GltfTextureInfo(
    textures[index],
    texCoord
        ?: 0
)

private fun NormalTextureInfoRaw.map(textures: List<GltfTexture>) = GltfNormalTextureInfo(
    textures[index],
    texCoord ?: 0,
    scale?.let(Number::toFloat) ?: 1f
)

private fun OcclusionTextureInfoRaw.map(textures: List<GltfTexture>) = GltfOcclusionTextureInfo(
    textures[index],
    texCoord ?: 0,
    strength?.let(Number::toFloat) ?: 1f
)

private fun PbrMetallicRoughnessRaw.map(textures: List<GltfTexture>) = GltfPbrMetallicRoughness(
    baseColorFactor?.let(GltfColor.Factory::fromNumbers) ?: GltfColor(),
    baseColorTexture?.map(textures),
    metallicFactor?.let(Number::toFloat) ?: 1f,
    roughnessFactor?.let(Number::toFloat) ?: 1f,
    metallicRoughnessTexture?.map(textures)
)

private fun MaterialRaw.map(index: Int, textures: List<GltfTexture>) = GltfMaterial(
    index,
    pbrMetallicRoughness?.map(textures) ?: GltfPbrMetallicRoughness(),
    normalTexture?.map(textures),
    occlusionTexture?.map(textures),
    emissiveTexture?.map(textures),
    emissiveFactor?.let(GltfColor.Factory::fromNumbers) ?: GltfColor(0f, 0f, 0f),
    alphaMode?.let(GltfAlphaMode.Factory::fromCode) ?: GltfAlphaMode.OPAQUE,
    alphaCutoff?.let(Number::toFloat) ?: 0.5f,
    doubleSided ?: false,
    name
)

private fun PrimitiveRaw.map(accessors: List<GltfAccessor>, materials: List<GltfMaterial>) = GltfPrimitive(
    attributes.mapValues { (_, accessorId) -> accessors[accessorId] },
    indices?.let(accessors::get),
    material?.let(materials::get) ?: GltfMaterial(-1),
    mode?.let(GltfPrimitiveMode.Factory::fromCode) ?: GltfPrimitiveMode.TRIANGLES,
    targets?.map { it.mapValues { (_, accessorId) -> accessors[accessorId] } }
)

private fun MeshRaw.map(index: Int, accessors: List<GltfAccessor>, materials: List<GltfMaterial>) = GltfMesh(
    index,
    primitives.map { it.map(accessors, materials) },
    weights?.map(Number::toFloat),
    name
)

private fun OrthographicRaw.map() = GltfOrthographic(
    xmag.toFloat(),
    ymag.toFloat(),
    zfar.toFloat(),
    znear.toFloat()
)

private fun PerspectiveRaw.map() = GltfPerspective(
    aspectRatio?.toFloat(),
    yfov.toFloat(),
    zfar?.toFloat(),
    znear.toFloat()
)

private fun CameraRaw.map(index: Int) = GltfCamera(
    index,
    orthographic?.map(),
    perspective?.map(),
    GltfCameraType.fromCode(type),
    name
)

/**
 * Map a node
 *
 * If the node has unmapped children, it maps them first
 */
private fun NodeRaw.map(
    index: Int,
    assetRaw: GltfAssetRaw,
    nodes: Array<GltfNode?>,
    cameras: List<GltfCamera>,
    meshes: List<GltfMesh>
) {
    if (nodes[index] != null) return

    val shouldMapChildren = children?.any { nodes[it] == null } ?: false
    if (shouldMapChildren) {
        children?.mapNotNull { Pair(it, assetRaw.nodes?.get(it) ?: return@mapNotNull null) }
            ?.forEach { (index, child) -> child.map(index, assetRaw, nodes, cameras, meshes) }
    }

    if (nodes[index] == null) nodes[index] = this.map(index, nodes, cameras, meshes)
}

private fun NodeRaw.map(
    index: Int,
    nodes: Array<GltfNode?>,
    cameras: List<GltfCamera>,
    meshes: List<GltfMesh>
): GltfNode {
    val mat = matrix?.let(GltfMat4.Factory::fromNumbers)

    return GltfNode(
        index = index,
        camera = camera?.let(cameras::get),
        children = children?.map(nodes::get)?.requireNoNulls(),
        matrix = mat ?: GltfMat4(),
        mesh = mesh?.let(meshes::get),
        rotation = mat?.let(GltfMat4::rotation)
            ?: rotation?.let(GltfQuaternion.Factory::fromNumbers)
            ?: GltfQuaternion(),
        scale = mat?.let(GltfMat4::scale)
            ?: scale?.let(GltfVec3.Factory::fromNumbers)
            ?: GltfVec3(1f, 1f, 1f),
        translation = mat?.let(GltfMat4::translation)
            ?: translation?.let(GltfVec3.Factory::fromNumbers)
            ?: GltfVec3(),
        weights = weights?.map(Number::toFloat),
        name = name,
        extensions = extensions,
        extras = extras
    )
}

private fun SkinRaw.map(index: Int, accessors: List<GltfAccessor>, nodes: List<GltfNode>) = GltfSkin(
    index,
    inverseBindMatrices?.let(accessors::get),
    skeleton?.let(nodes::get),
    joints.map(nodes::get).requireNoNulls(),
    name
)

private fun AnimationSamplerRaw.map(accessors: List<GltfAccessor>) = GltfAnimationSampler(
    accessors[input],
    interpolation?.let(GltfInterpolationType.Factory::fromCode) ?: GltfInterpolationType.LINEAR,
    accessors[output]
)

private fun AnimationTargetRaw.map(nodes: List<GltfNode>) = GltfAnimationTarget(
    node?.let(nodes::get),
    GltfAnimationTargetPath.fromCode(path)
)

private fun ChannelRaw.map(samplers: List<GltfAnimationSampler>, nodes: List<GltfNode>) = GltfChannel(
    samplers[sampler],
    target.map(nodes)
)

private fun AnimationRaw.map(accessors: List<GltfAccessor>, nodes: List<GltfNode>): GltfAnimation {
    val samplers = samplers.map { it.map(accessors) }
    return GltfAnimation(channels.map { it.map(samplers, nodes) }, samplers, name)
}

private fun SceneRaw.map(index: Int, gltfNodes: List<GltfNode>) = GltfScene(
    index,
    nodes?.map(gltfNodes::get),
    name
)
