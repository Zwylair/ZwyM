package zwylair.zwym

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import net.fabricmc.api.ModInitializer
import net.minecraft.block.BlockState
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import zwylair.zwym.blocks.ModBlocks
import zwylair.zwym.blocks.entities.ModBlockEntities
import zwylair.zwym.customevents.LightningRodCallback
import zwylair.zwym.entities.ModEntities
import zwylair.zwym.events.copperBlockToElectrifiedCopperBlock
import zwylair.zwym.itemgroups.ModItemGroups
import zwylair.zwym.items.ModItems
import zwylair.zwym.soundevents.ModSoundEvents

class ZwyM : ModInitializer {
    companion object {
        const val MODID = "zwym"
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MODID)
        fun id(path: String) = Identifier(MODID, path)
    }

    override fun onInitialize() {
        LOGGER.info("ZwyM has started initialization...")

        ModItemGroups.init()
        ModBlocks.init()
        ModItems.init()
        ModBlockEntities.init()
        ModEntities.init()
        ModSoundEvents.init()

        LightningRodCallback.EVENT.register { state: BlockState, world: World, pos: BlockPos -> copperBlockToElectrifiedCopperBlock(state, world, pos) }

        LOGGER.info("")
        LOGGER.info("ZwyM has been initialized!")
        LOGGER.info("")
    }
}
