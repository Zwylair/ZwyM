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
import org.slf4j.event.Level

import zwylair.zwym.customevents.LightningRodCallback
import zwylair.zwym.events.copperBlockToElectrifiedCopperBlock
import zwylair.zwym.itemgroups.ItemGroups
import zwylair.zwym.items.ModItems
import zwylair.zwym.blocks.ModBlocks
import zwylair.zwym.utils.Utils.register


class ZwyM : ModInitializer {
    companion object {
        const val MODID = "zwym"
        val LOGGER: Logger = LoggerFactory.getLogger(MODID)
        fun id(path: String) = Identifier(MODID, path)
        val registryQueue: List<Any> = listOf(
            ModBlocks.ElectrifiedCopperBlock,
            ModItems.StormScrollItem
        )
    }

    override fun onInitialize() {
        LOGGER.atLevel(Level.DEBUG)
        LOGGER.info("ZwyM has started initialization...")

        Registry.register(Registries.ITEM_GROUP, ItemGroups.mainItemGroupId, ItemGroups.mainItemGroup)
        registryQueue.forEach {
            if (it is ModObject.ModBlock) {
                LOGGER.debug("Trying to register {}", it.id)
                register(it)
            }
            else if (it is ModObject.ModItem) {
                LOGGER.info("Trying to register {}", it.id)
                register(it)
            }
        }
        LightningRodCallback.EVENT.register { state: BlockState, world: World, pos: BlockPos -> copperBlockToElectrifiedCopperBlock(state, world, pos) }

        LOGGER.info("ZwyM has been initialized!")
    }
}
