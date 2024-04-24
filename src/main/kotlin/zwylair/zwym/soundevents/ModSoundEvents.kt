package zwylair.zwym.soundevents

import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import zwylair.zwym.ZwyM

class ModSoundEvent(val id: Identifier)


object ModSoundEvents {
    val NoSoundModObject = ModSoundEvent(ZwyM.id("no_sound"))
    val NO_SOUND_SOUND_EVENT: SoundEvent = SoundEvent.of(NoSoundModObject.id)
}
