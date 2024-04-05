package zwylair.zwym.damagetypes

import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageType
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.World

import zwylair.zwym.ZwyM

object DamageTypes {
    val ElectricityDamageType: RegistryKey<DamageType> = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, ZwyM.id("electricity"))

    fun of(world: World, key: RegistryKey<DamageType>?): DamageSource {
        return DamageSource(world.registryManager.get(RegistryKeys.DAMAGE_TYPE).entryOf(key))
    }
}
