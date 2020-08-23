package cofh.lib.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.world.World;

public abstract class AbstractGrenadeEntity extends ProjectileItemEntity {

    public int radius = 5;

    public AbstractGrenadeEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public AbstractGrenadeEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {

        super(type, x, y, z, worldIn);
    }

    public AbstractGrenadeEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {

        super(type, livingEntityIn, worldIn);
    }

    public AbstractGrenadeEntity setRadius(int radius) {

        this.radius = radius;
        return this;
    }

}
