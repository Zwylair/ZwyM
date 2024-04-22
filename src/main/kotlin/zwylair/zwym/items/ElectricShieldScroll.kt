package zwylair.zwym.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import zwylair.zwym.ZwyM
import zwylair.zwym.ModObject.ModItem
import zwylair.zwym.itemgroups.ItemGroups


class ElectricShieldScroll : ModItem(FabricItemSettings().maxCount(8)) {
    override var id = ZwyM.id("electric_shield_scroll")
    override var itemGroupAddTo: RegistryKey<ItemGroup>? = ItemGroups.mainItemGroupRegKey

    override fun use(world: World?, player: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val itemStack = player!!.getStackInHand(hand)
        if (world!!.isClient) return TypedActionResult.fail(itemStack)

        return TypedActionResult.success(itemStack)
    }
}