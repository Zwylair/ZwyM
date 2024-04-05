package zwylair.zwym

import net.fabricmc.api.ModInitializer
import net.minecraft.block.BlockState

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import zwylair.zwym.blocks.ModBlocks
import zwylair.zwym.customevents.LightningRodCallback;
import zwylair.zwym.events.copperBlockToElectrifiedCopperBlock
import zwylair.zwym.itemgroups.ItemGroups


class ZwyM : ModInitializer {
    companion object {
        const val MODID = "zwym"
        @JvmStatic
        val LOGGER: Logger = LoggerFactory.getLogger(MODID)
        fun id(path: String) = Identifier(MODID, path)
    }

    override fun onInitialize() {
        LOGGER.info("ZwyM has started initialization...")

        Registry.register(Registries.ITEM_GROUP, ItemGroups.mainItemGroupId, ItemGroups.mainItemGroup)
        ModBlocks.register()
        LightningRodCallback.EVENT.register { state: BlockState, world: World, pos: BlockPos -> copperBlockToElectrifiedCopperBlock(state, world, pos) }

        LOGGER.info("ZwyM has been initialized!")
    }
}
