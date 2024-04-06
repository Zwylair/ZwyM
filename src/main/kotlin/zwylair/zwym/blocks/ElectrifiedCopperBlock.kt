package zwylair.zwym.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import zwylair.zwym.ModObject.ModBlock

import zwylair.zwym.ZwyM
import zwylair.zwym.damagetypes.DamageTypes
import zwylair.zwym.itemgroups.ItemGroups


class ElectrifiedCopperBlock : ModBlock(
    FabricBlockSettings.copy(Blocks.COPPER_BLOCK)
        .luminance { 7 }
) {
    override var id = ZwyM.id("electrified_copper_block")
    override var itemGroupAddTo: RegistryKey<ItemGroup>? = ItemGroups.mainItemGroupRegKey

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState?, world: World, pos: BlockPos?, player: PlayerEntity, hand: Hand?, hit: BlockHitResult?
    ): ActionResult {
        if (world.isClient) return ActionResult.FAIL
        if (player.getStackInHand(hand).isEmpty) {
            player.damage(DamageTypes.of(world, DamageTypes.ElectricityDamageType), 6f)
            return ActionResult.SUCCESS
        }
        return ActionResult.FAIL
    }
}
