package zwylair.zwym.itemgroups

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text

import zwylair.zwym.ZwyM
import zwylair.zwym.blocks.ModBlocks

object ItemGroups {
    val mainItemGroup: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(ModBlocks.ElectrifiedCopperBlock) }
        .displayName(Text.translatable("itemGroup.${ZwyM.MODID}.main_item_group"))
        .build()
    val mainItemGroupId = ZwyM.id("main_item_group")
    val mainItemGroupRegKey: RegistryKey<ItemGroup> = RegistryKey.of(RegistryKeys.ITEM_GROUP, mainItemGroupId)
}
