package zwylair.zwym.blocks

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import zwylair.zwym.ModObject.GlintedBlockItem
import zwylair.zwym.ModObject.ModBlock
import zwylair.zwym.ModObject.ModBlockItem
import zwylair.zwym.ZwyM

object ModBlocks {
    lateinit var ELECTRIFIED_COPPER_BLOCK: ModBlock
    lateinit var EXAMPLE_BLOCK: ModBlock

    fun init() {
        ELECTRIFIED_COPPER_BLOCK = register(ElectrifiedCopperBlock())
        EXAMPLE_BLOCK = register(ExampleBlock())
    }

    private fun register(block: ModBlock): ModBlock {
        val blockItem: ModBlockItem = if (block.glinted) GlintedBlockItem(block, FabricItemSettings()) else ModBlockItem(block, FabricItemSettings())

        Registry.register(Registries.BLOCK, block.id, block)
        Registry.register(Registries.ITEM, block.id, blockItem)
        ZwyM.LOGGER.info("")
        ZwyM.LOGGER.info("{} Block, BlockItem registered", block.translationKey)
        if (block.itemGroupAddTo != null) addToGroup(blockItem)

        return block
    }

    private fun addToGroup(blockItem: ModBlockItem) {
        ItemGroupEvents.modifyEntriesEvent(blockItem.itemGroupAddTo).register(ItemGroupEvents.ModifyEntries { it.add(blockItem) })
        ZwyM.LOGGER.info("{} BlockItem added into {} ItemGroup", blockItem.translationKey, blockItem.itemGroupAddTo?.value?.toTranslationKey())
    }
}
