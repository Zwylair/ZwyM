package zwylair.zwym

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import zwylair.zwym.blocks.ModBlocks
import zwylair.zwym.customevents.LightningRodCallback
import zwylair.zwym.entities.CubeEntity
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
        )
        @JvmField
        val CUBE: EntityType<CubeEntity> = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier("zwym", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE) {
                entityType: EntityType<CubeEntity>, world: World ->
                CubeEntity(entityType, world)
            }
                .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                .build()
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
            }
        }
        LightningRodCallback.EVENT.register { state: BlockState, world: World, pos: BlockPos -> copperBlockToElectrifiedCopperBlock(state, world, pos) }

        FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());

        LOGGER.info("")
        LOGGER.info("ZwyM has been initialized!")
        LOGGER.info("")
    }
}
