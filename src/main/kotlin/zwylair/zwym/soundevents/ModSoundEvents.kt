package zwylair.zwym.soundevents

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM

object ModSoundEvents {
    lateinit var NO_SOUND_SOUND_EVENT: SoundEvent

    fun init() {
        NO_SOUND_SOUND_EVENT = register(ZwyM.id("no_sound"))
    }

    private fun register(identifier: Identifier): SoundEvent {
        val soundEvent = Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier))
        ZwyM.LOGGER.info("")
        ZwyM.LOGGER.info("{} SoundEvent registered", identifier.toTranslationKey())
        return soundEvent
    }
}
