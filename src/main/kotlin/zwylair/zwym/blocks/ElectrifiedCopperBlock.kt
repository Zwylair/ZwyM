package zwylair.zwym.blocks

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import zwylair.zwym.ZwyM
import zwylair.zwym.damagetypes.DamageTypes


class ElectrifiedCopperBlock : Block(
    FabricBlockSettings.copy(Blocks.COPPER_BLOCK)
        .luminance { 7 }
) {
    val id = ZwyM.id("electrified_copper_block")

    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState?, world: World, pos: BlockPos?, player: PlayerEntity, hand: Hand?, hit: BlockHitResult?
    ): ActionResult {
        if (world.isClient) return ActionResult.FAIL
        player.damage(DamageTypes.of(world, DamageTypes.ElectricityDamageType), 999f)
        return ActionResult.SUCCESS
    }
}
