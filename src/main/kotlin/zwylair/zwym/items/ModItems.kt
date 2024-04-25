package zwylair.zwym.items

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import zwylair.zwym.ModObject.ModItem
import zwylair.zwym.ZwyM

object ModItems {
    lateinit var STORM_SCROLL: ModItem
    lateinit var ELECTRIC_SHIELD_SCROLL: ModItem

    fun init() {
        STORM_SCROLL = register(StormScroll())
        ELECTRIC_SHIELD_SCROLL = register(ElectricShieldScroll())
    }

    private fun register(item: ModItem): ModItem {
        Registry.register(Registries.ITEM, item.id, item)
        ZwyM.LOGGER.info("\n{} Item registered", item.translationKey)
        if (item.itemGroupAddTo != null) addToGroup(item)

        return item
    }

    private fun addToGroup(item: ModItem) {
        ItemGroupEvents.modifyEntriesEvent(item.itemGroupAddTo).register(ItemGroupEvents.ModifyEntries { it.add(item) })
        ZwyM.LOGGER.info("{} Item added into {} ItemGroup", item.translationKey, item.itemGroupAddTo?.value?.toTranslationKey())
    }
}
