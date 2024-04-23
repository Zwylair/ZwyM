package zwylair.zwym.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;


public class CubeEntity extends MobEntity {
    private PlayerEntity owner;

    public CubeEntity(EntityType<? extends MobEntity> type, World world) {
        super(type, world);
    }

    public void setOwner(PlayerEntity player) {
        this.owner = player;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.owner != null) {
            double distance = 1.70; // Расстояние от игрока, на котором должен находиться энтити
            double angle = this.getWorld().getTime() * 0.5; // Угол вращения, можно изменить скорость вращения
            double offsetX = Math.cos(angle) * distance;
            double offsetZ = Math.sin(angle) * distance;
            double playerX = this.owner.getX();
            double playerY = this.owner.getY();
            double playerZ = this.owner.getZ();
            this.updatePosition(playerX + offsetX, playerY, playerZ + offsetZ);
            // Вычисляем угол между энтити и игроком
            double angleToPlayer = Math.atan2(offsetZ, offsetX);
            // Преобразуем радианы в градусы и добавляем 90 градусов (чтобы энтити смотрел в сторону игрока)
            float yaw = (float) Math.toDegrees(angleToPlayer) + 90.0f;
            this.setYaw(yaw);
        } else {
            this.setOwner(this.getWorld().getClosestPlayer(this, 3.0));
        }
    }
}
