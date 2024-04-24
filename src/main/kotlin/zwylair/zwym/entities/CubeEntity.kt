package zwylair.zwym.entities

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import net.minecraft.entity.EntityType
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.sound.SoundEvent
import net.minecraft.world.World
import zwylair.zwym.soundevents.ModSoundEvents
import zwylair.zwym.ZwyM

class CubeEntity(type: EntityType<out MobEntity?>?, world: World?) : MobEntity(type, world) {
    private var owner: PlayerEntity? = null
    private var aliveTicks: Int = 0
    private val aliveTicksLimit: Int = 5 * 20

    override fun getHurtSound(source: DamageSource?): SoundEvent? { return null }
    override fun getDeathSound(): SoundEvent? { return null }
    override fun getFallSounds(): FallSounds {
        return FallSounds(ModSoundEvents.NO_SOUND_SOUND_EVENT, ModSoundEvents.NO_SOUND_SOUND_EVENT)
    }

    override fun playSound(sound: SoundEvent, volume: Float, pitch: Float) {
        if ("fall" in sound.id.toTranslationKey().toString()) { return }
        super.playSound(sound, volume, pitch)
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (source.isIn(DamageTypeTags.IS_PROJECTILE) ||
            source.isIn(DamageTypeTags.BYPASSES_ARMOR) &&
            !source.isIn(DamageTypeTags.IS_FALL) &&
            !source.isIn(DamageTypeTags.IS_DROWNING) ) {

            return super.damage(source, 999f)
        }
        return false
    }

    override fun tick() {
        aliveTicks += 1
        super.tick()

        if (aliveTicks == aliveTicksLimit) {
            super.kill()
            return
        }

        if (owner != null) {
            val distance = 1.6  // distance from the player at which the entity should be positioned
            val angle = world.time * 0.25  // rotation angle, you can adjust the rotation speed
            val offsetX = cos(angle) * distance
            val offsetZ = sin(angle) * distance
            val playerX = owner!!.x
            val playerY = owner!!.y
            val playerZ = owner!!.z

            this.updatePosition(playerX + offsetX, playerY + 1, playerZ + offsetZ)
            // calculate the angle between the entity and the player
            val angleToPlayer = atan2(offsetZ, offsetX)
            // convert radians to degrees and add 90 degrees (to make the entity face the player)
            val yaw = Math.toDegrees(angleToPlayer).toFloat()
            this.yaw = yaw
        } else {
            this.setOwner(world.getClosestPlayer(this, 3.0))
        }
    }

    fun setOwner(player: PlayerEntity?) { owner = player }
}
