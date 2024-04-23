package zwylair.zwym.entities

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

object ModEntities {
    @JvmField
    val CubeEntityType: EntityType<CubeEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier("zwym", "cube"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE) {
                entityType: EntityType<CubeEntity>, world: World ->
            CubeEntity(entityType, world)
        }
            .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
            .build()
    )
    private val CubeEntityAttributes = MobEntity.createMobAttributes()

    val EntityTypeToAttributes: Map<EntityType<*>, DefaultAttributeContainer.Builder> = mapOf(
        CubeEntityType to CubeEntityAttributes
    )
}
