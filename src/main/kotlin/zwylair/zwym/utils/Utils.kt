package zwylair.zwym.utils

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World
import zwylair.zwym.ModObject.ModBlockItem
import zwylair.zwym.ModObject.ModItem
import zwylair.zwym.ModObject.ModBlock
import zwylair.zwym.ModObject.GlintedBlockItem
import zwylair.zwym.ZwyM
import zwylair.zwym.entities.CubeEntity

object Utils {
    private fun addToGroup(item: ModItem) {
        ItemGroupEvents.modifyEntriesEvent(item.itemGroupAddTo).register(ItemGroupEvents.ModifyEntries { it.add(item) })
        ZwyM.LOGGER.info("{} Item added into {} ItemGroup", item.translationKey, item.itemGroupAddTo?.value?.toTranslationKey())
    }

    private fun addToGroup(blockItem: ModBlockItem) {
        ItemGroupEvents.modifyEntriesEvent(blockItem.itemGroupAddTo).register(ItemGroupEvents.ModifyEntries { it.add(blockItem) })
        ZwyM.LOGGER.info("{} BlockItem added into {} ItemGroup", blockItem.translationKey, blockItem.itemGroupAddTo?.value?.toTranslationKey())
    }

    fun register(item: ModItem) {
        Registry.register(Registries.ITEM, item.id, item)
        ZwyM.LOGGER.info("{} Item registered", item.translationKey)
        if (item.itemGroupAddTo != null) addToGroup(item)
    }

    fun register(block: ModBlock) {
        val blockItem: ModBlockItem = if (block.glinted) GlintedBlockItem(block, FabricItemSettings()) else ModBlockItem(block, FabricItemSettings())

        Registry.register(Registries.BLOCK, block.id, block)
        Registry.register(Registries.ITEM, block.id, blockItem)
        ZwyM.LOGGER.info("{} Block registered", block.translationKey)
        if (block.itemGroupAddTo != null) addToGroup(blockItem)
    }

    fun register(itemGroup: ItemGroup, id: Identifier) {
        Registry.register(Registries.ITEM_GROUP, id, itemGroup)
        ZwyM.LOGGER.info("{} ItemGroup registered", id.toTranslationKey().toString())
    }

//    private fun extractItemSettings(item: Item): Item.Settings {
//        val settings = FabricItemSettings()
//            .maxCount(item.maxCount)
//            .food(item.foodComponent)
//            .maxDamage(item.maxDamage)
//            .rarity(item.getRarity(item.defaultStack))
//            .recipeRemainder(item.recipeRemainder)
//        if (item.isFireproof) settings.fireproof()
//
//        return settings
//    }
}
