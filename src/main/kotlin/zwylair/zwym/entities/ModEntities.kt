package zwylair.zwym.entities

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World
import zwylair.zwym.ZwyM

object ModEntities {
    @JvmField
    val CubeEntityType: EntityType<CubeEntity> = FabricEntityTypeBuilder.create(
        SpawnGroup.CREATURE
    ) { entityType: EntityType<CubeEntity>, world: World ->
        CubeEntity(entityType, world)
    }
        .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
        .build()

    fun init() {
        register(
            CubeEntityType,
            MobEntity.createMobAttributes(),
            ZwyM.id("cube")
        )
    }

    private fun <T : MobEntity> register(entityType: EntityType<T>, entityAttributes: DefaultAttributeContainer.Builder, id: Identifier) {
        Registry.register(Registries.ENTITY_TYPE, id, entityType)
        FabricDefaultAttributeRegistry.register(entityType, entityAttributes)
        ZwyM.LOGGER.info("")
        ZwyM.LOGGER.info("{} Entity registered", id)
    }
}
