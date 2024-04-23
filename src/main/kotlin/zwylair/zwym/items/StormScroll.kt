package zwylair.zwym.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import zwylair.zwym.ModObject.ModItem
import zwylair.zwym.ZwyM
import zwylair.zwym.itemgroups.ItemGroups

class StormScroll : ModItem(FabricItemSettings().maxCount(8)) {
    override var id = ZwyM.id("storm_scroll")
    override var itemGroupAddTo: RegistryKey<ItemGroup>? = ItemGroups.mainItemGroupRegKey

    override fun use(world: World?, player: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val itemStack = player!!.getStackInHand(hand)
        if (world!!.isClient) return TypedActionResult.fail(itemStack)

        world.server?.overworld?.setWeather(20, 2000, false, true)
        itemStack.decrement(1)
        return TypedActionResult.success(itemStack)
    }
}
