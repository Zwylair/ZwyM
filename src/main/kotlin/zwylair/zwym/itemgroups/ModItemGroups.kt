package zwylair.zwym.itemgroups

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM
import zwylair.zwym.blocks.ModBlocks

object ModItemGroups {
    lateinit var ZWYM_ITEMGROUP_ID: Identifier
    lateinit var ZWYM_ITEMGROUP_REG_KEY: RegistryKey<ItemGroup>
    lateinit var ZWYM_ITEMGROUP: ItemGroup

    fun init() {
        ZWYM_ITEMGROUP_ID = ZwyM.id("zwym_itemgroup")
        ZWYM_ITEMGROUP = FabricItemGroup.builder()
            .icon { ItemStack(ModBlocks.ELECTRIFIED_COPPER_BLOCK) }
            .displayName(Text.translatable("itemGroup.${ZWYM_ITEMGROUP_ID}"))
            .build()

        ZWYM_ITEMGROUP_REG_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, ZWYM_ITEMGROUP_ID)

        register(ZWYM_ITEMGROUP, ZWYM_ITEMGROUP_ID)
    }

    private fun register(itemGroup: ItemGroup, id: Identifier) {
        Registry.register(Registries.ITEM_GROUP, id, itemGroup)
        ZwyM.LOGGER.info("\n{} ItemGroup registered", id.toTranslationKey())
    }
}
