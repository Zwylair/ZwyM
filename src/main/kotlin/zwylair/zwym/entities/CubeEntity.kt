package zwylair.zwym.entities

import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class CubeEntity(type: EntityType<out MobEntity?>?, world: World?) : MobEntity(type, world) {
    private var owner: PlayerEntity? = null

    fun setOwner(player: PlayerEntity?) { this.owner = player }

    override fun tick() {
        super.tick()

        if (this.owner != null) {
            val distance = 1.70 // Расстояние от игрока, на котором должен находиться энтити
            val angle = world.time * 0.25 // Угол вращения, можно изменить скорость вращения
            val offsetX = cos(angle) * distance
            val offsetZ = sin(angle) * distance
            val playerX = owner!!.x
            val playerY = owner!!.y
            val playerZ = owner!!.z
            this.updatePosition(playerX + offsetX, playerY, playerZ + offsetZ)
            // Вычисляем угол между энтити и игроком
            val angleToPlayer = atan2(offsetZ, offsetX)
            // Преобразуем радианы в градусы и добавляем 90 градусов (чтобы энтити смотрел в сторону игрока)
            val yaw = Math.toDegrees(angleToPlayer).toFloat() + 90.0f
            this.yaw = yaw
        } else {
            this.setOwner(world.getClosestPlayer(this, 3.0))
        }
    }
}
