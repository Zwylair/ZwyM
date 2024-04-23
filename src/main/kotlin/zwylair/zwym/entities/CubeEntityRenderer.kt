package zwylair.zwym.entities

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyMClient

/*
* A renderer is used to provide an entity model, shadow size, and texture.
*/
class CubeEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<CubeEntity?, CubeEntityModel?>(
        context,
        CubeEntityModel(context.getPart(ZwyMClient.MODEL_CUBE_LAYER)),
        0.5f
    ) {
    override fun getTexture(entity: CubeEntity?): Identifier {
        return Identifier("zwym", "textures/entity/cube/cube.png")
    }
}