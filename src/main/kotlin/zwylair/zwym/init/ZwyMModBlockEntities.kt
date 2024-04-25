/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import zwylair.zwym.ZwyM
import zwylair.zwym.blocks.entities.ExampleBlockBlockEntity

object ZwyMModBlockEntities {
    var EXAMPLE_BLOCK: BlockEntityType<*>? = null

    fun load() {
        EXAMPLE_BLOCK = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier(ZwyM.MODID, "example_block"),
            FabricBlockEntityTypeBuilder.create(
                { position: BlockPos?, state: BlockState? ->
                    ExampleBlockBlockEntity(
                        position,
                        state
                    )
                }, ZwyMModBlocks.EXAMPLE_BLOCK
            ).build(null)
        )
    }
}
