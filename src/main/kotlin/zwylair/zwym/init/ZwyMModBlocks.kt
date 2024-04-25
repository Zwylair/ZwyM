/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init

import net.minecraft.block.Block
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM
import zwylair.zwym.blocks.ExampleBlockBlock

object ZwyMModBlocks {
    var EXAMPLE_BLOCK: Block? = null

    fun load() {
        EXAMPLE_BLOCK = register("example_block", ExampleBlockBlock())
    }

    fun clientLoad() {
        ExampleBlockBlock.clientInit()
    }

    private fun register(registryName: String, block: Block): Block {
        return Registry.register(Registries.BLOCK, Identifier(ZwyM.MODID, registryName), block)
    }
}
