package zwylair.zwym.blocks

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.enums.Instrument
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContextParameterSet
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import zwylair.zwym.blocks.entities.ExampleBlockBlockEntity
import zwylair.zwym.init.ZwyMModBlocks

class ExampleBlockBlock : Block(PROPERTIES),
    BlockEntityProvider {
    override fun getOpacity(state: BlockState, worldIn: BlockView, pos: BlockPos): Int {
        return 15
    }

    override fun getDroppedStacks(state: BlockState, builder: LootContextParameterSet.Builder): List<ItemStack> {
        val dropsOriginal = super.getDroppedStacks(state, builder)
        if (!dropsOriginal.isEmpty()) return dropsOriginal
        return listOf(ItemStack(this, 1))
    }

    override fun onUse(
        blockstate: BlockState,
        world: World,
        pos: BlockPos,
        entity: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        super.onUse(blockstate, world, pos, entity, hand, hit)
        if (!world.isClient) {
            val menuProvider = blockstate.createScreenHandlerFactory(world, pos)
            if (menuProvider != null) entity.openHandledScreen(menuProvider)
        }
        return ActionResult.SUCCESS
    }

    override fun createScreenHandlerFactory(
        state: BlockState,
        worldIn: World,
        pos: BlockPos
    ): NamedScreenHandlerFactory? {
        val tileEntity = worldIn.getBlockEntity(pos)
        return if (tileEntity is NamedScreenHandlerFactory) tileEntity else null
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return ExampleBlockBlockEntity(pos, state)
    }

    override fun onSyncedBlockEvent(
        state: BlockState,
        world: World,
        pos: BlockPos,
        eventID: Int,
        eventParam: Int
    ): Boolean {
        super.onSyncedBlockEvent(state, world, pos, eventID, eventParam)
        val blockEntity = world.getBlockEntity(pos)
        return blockEntity?.onSyncedBlockEvent(eventID, eventParam) ?: false
    }

    override fun onStateReplaced(
        state: BlockState,
        world: World,
        pos: BlockPos,
        newState: BlockState,
        isMoving: Boolean
    ) {
        if (state.block !== newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is ExampleBlockBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity)
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, isMoving)
        }
    }

    companion object {
        var PROPERTIES: Settings =
            Settings.create().instrument(Instrument.BASEDRUM).sounds(BlockSoundGroup.GRAVEL).strength(1f, 10f)

        @Environment(EnvType.CLIENT)
        fun clientInit() {
            BlockRenderLayerMap.INSTANCE.putBlock(ZwyMModBlocks.EXAMPLE_BLOCK, RenderLayer.getSolid())
        }
    }
}
