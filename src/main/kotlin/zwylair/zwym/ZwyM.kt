package zwylair.zwym

import net.fabricmc.api.ModInitializer
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import zwylair.zwym.blocks.ModBlocks
import zwylair.zwym.customevents.LightningRodCallback
import zwylair.zwym.entities.ModEntities
import zwylair.zwym.entities.ModEntities.EntityTypeToAttributes
import zwylair.zwym.events.copperBlockToElectrifiedCopperBlock
import zwylair.zwym.itemgroups.ItemGroups
import zwylair.zwym.items.ModItems
import zwylair.zwym.utils.Utils.register


class ZwyM : ModInitializer {
    companion object {
        const val MODID = "zwym"
        @JvmField
        val LOGGER: Logger = LoggerFactory.getLogger(MODID)
        fun id(path: String) = Identifier(MODID, path)
        val registryQueue: List<Any> = listOf(
            ItemGroups.mainItemGroup,
            ModBlocks.ElectrifiedCopperBlock,
            ModItems.StormScrollItem,
            ModItems.ElectricShieldScrollItem,
            ModEntities.CubeEntityType,
        )
    }

    override fun onInitialize() {
        LOGGER.info("ZwyM has started initialization...")

        registryQueue.forEach {
            LOGGER.info("")
            when (it) {
                is ModObject.ModBlock -> {
                    LOGGER.info("Trying to register {}", it.id)
                    register(it)
                }
                is ModObject.ModItem -> {
                    LOGGER.info("Trying to register {}", it.id)
                    register(it)
                }
                is ItemGroup -> {
                    LOGGER.info("Trying to register {}", ItemGroups.itemGroupsIds[it]!!.toTranslationKey())
                    register(it, ItemGroups.itemGroupsIds[it]!!)
                }
                is EntityType<*> -> {  // it is EntityType<MobEntity> 100%
                    LOGGER.info("Trying to register {}", it.translationKey)
                    register(it as EntityType<MobEntity>, EntityTypeToAttributes[it]!!)
                }
            }
        }
        LightningRodCallback.EVENT.register { state: BlockState, world: World, pos: BlockPos -> copperBlockToElectrifiedCopperBlock(state, world, pos) }

        LOGGER.info("")
        LOGGER.info("ZwyM has been initialized!")
        LOGGER.info("")
    }
}
