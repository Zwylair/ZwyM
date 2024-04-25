package zwylair.zwym.entities

import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM
import zwylair.zwym.ZwyMClient

/*
* A renderer is used to provide an entity model, shadow size, and texture.
*/
class CubeEntityRenderer(context: EntityRendererFactory.Context) :
    MobEntityRenderer<CubeEntity?, CubeEntityModel?>(
        context,
        CubeEntityModel(context.getPart(ZwyMClient.MODEL_CUBE_LAYER)),
        0.3f
    ) {
    override fun getTexture(entity: CubeEntity?): Identifier {
        return ZwyM.id("textures/entity/cube/cube.png")
    }
}
