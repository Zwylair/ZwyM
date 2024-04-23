package zwylair.zwym

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier
import zwylair.zwym.entities.CubeEntityModel
import zwylair.zwym.entities.CubeEntityRenderer
import zwylair.zwym.entities.ModEntities

@Environment(EnvType.CLIENT)
class ZwyMClient : ClientModInitializer {
    override fun onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        EntityRendererRegistry.register(ModEntities.CubeEntityType
        ) { context: EntityRendererFactory.Context? -> CubeEntityRenderer(context!!) }

        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::texturedModelData)
    }

    companion object {
        val MODEL_CUBE_LAYER: EntityModelLayer = EntityModelLayer(Identifier("zwym", "cube"), "main")
    }
}
