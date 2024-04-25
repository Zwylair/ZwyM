package zwylair.zwym.events

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import zwylair.zwym.blocks.ModBlocks.ELECTRIFIED_COPPER_BLOCK

fun copperBlockToElectrifiedCopperBlock(state: BlockState, world: World, pos: BlockPos): ActionResult {
    if (world.isClient()) { return ActionResult.FAIL }

    val belowBlockPos = pos.add(0, -1, 0)
    if (world.getBlockState(belowBlockPos).block == Blocks.COPPER_BLOCK)
        world.setBlockState(belowBlockPos, ELECTRIFIED_COPPER_BLOCK.defaultState)
    return ActionResult.PASS
}
