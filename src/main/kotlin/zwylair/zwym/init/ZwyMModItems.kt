/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package zwylair.zwym.init

import net.minecraft.client.item.ClampedModelPredicateProvider
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM

object ZwyMModItems {
    var EXAMPLE_BLOCK: Item? = null

    fun load() {
        EXAMPLE_BLOCK = register("example_block", BlockItem(ZwyMModBlocks.EXAMPLE_BLOCK, Item.Settings()))
    }

    fun clientLoad() {
    }

    private fun register(registryName: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(ZwyM.MODID, registryName), item)
    }

    private fun registerBlockingProperty(item: Item) {
        ModelPredicateProviderRegistry.register(
            item,
            Identifier("blocking"),
            ModelPredicateProviderRegistry.get(Items.SHIELD, Identifier("blocking")) as ClampedModelPredicateProvider?
        )
    }
}
