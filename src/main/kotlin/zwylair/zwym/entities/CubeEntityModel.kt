package zwylair.zwym.entities

import com.google.common.collect.ImmutableList
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.util.math.MatrixStack
import java.util.function.Consumer

class CubeEntityModel(modelPart: ModelPart) : EntityModel<CubeEntity?>() {
    private val base: ModelPart = modelPart.getChild(EntityModelPartNames.CUBE)

    override fun setAngles(
        entity: CubeEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {}

    override fun render(
        matrices: MatrixStack,
        vertices: VertexConsumer,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        ImmutableList.of(this.base).forEach(Consumer { modelRenderer: ModelPart ->
            modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha)
        })
    }

    // You can use BlockBench, make your model and export it to get this method for your entity model.
    companion object {
        @JvmStatic
        val texturedModelData: TexturedModelData
            get() {
                val modelData = ModelData()
                val modelPartData = modelData.root
                modelPartData.addChild(
                    EntityModelPartNames.CUBE,
                    ModelPartBuilder.create().uv(0, 0).cuboid(-6f, 12f, -6f, 12f, 12f, 12f),
                    ModelTransform.pivot(0f, 0f, 0f)
                )

                return TexturedModelData.of(modelData, 64, 64)
            }
    }
}
