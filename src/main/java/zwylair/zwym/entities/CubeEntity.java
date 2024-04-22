package zwylair.zwym.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class CubeEntity extends MobEntity {
    public CubeEntity(EntityType<? extends MobEntity> type, World world) {
        super(type, world);
    }
}
