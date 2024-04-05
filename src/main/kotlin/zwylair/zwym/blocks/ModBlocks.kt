package zwylair.zwym.blocks

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

import zwylair.zwym.ZwyM
import zwylair.zwym.itemgroups.ItemGroups


object ModBlocks {
    val ElectrifiedCopperBlock = ElectrifiedCopperBlock()

    fun register() {
        registerBlockAndItem(ElectrifiedCopperBlock.id, ElectrifiedCopperBlock, ItemGroups.mainItemGroupRegKey, true)
    }

    private fun addItemToGroup(item: Item, tabList: RegistryKey<ItemGroup>) {
        ItemGroupEvents.modifyEntriesEvent(tabList).register(ModifyEntries { it.add(item) } )
    }

    private fun registerBlockAndItem(identifier: Identifier, block: Block, addToTabList: RegistryKey<ItemGroup>?,
                                     isItemGlinted: Boolean = false) {

        val blockItem: BlockItem = if (isItemGlinted) GlintedBlockItem(block, FabricItemSettings()) else BlockItem(block, FabricItemSettings())
        Registry.register(Registries.BLOCK, identifier, block)
        Registry.register(Registries.ITEM, identifier, blockItem)
        if (addToTabList != null) addItemToGroup(blockItem, addToTabList)
    }
}

class GlintedBlockItem(block: Block, settings: Settings) : BlockItem(block, settings) {
    override fun hasGlint(stack: ItemStack?): Boolean { return true }
}
