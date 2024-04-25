package zwylair.zwym.blocks.entities

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.math.BlockPos
import zwylair.zwym.ZwyM
import zwylair.zwym.blocks.ModBlocks

object ModBlockEntities {
    lateinit var EXAMPLE_BLOCK: BlockEntityType<*>

    fun init() {
        EXAMPLE_BLOCK = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            ZwyM.id("example_block"),
            FabricBlockEntityTypeBuilder.create(
                { position: BlockPos?, state: BlockState? -> ExampleBlockEntity(position, state) },
                ModBlocks.EXAMPLE_BLOCK
            ).build(null)
        )
    }
}
