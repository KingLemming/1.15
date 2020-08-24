package cofh.thermal.core.entity.projectile;

import cofh.lib.entity.AbstractGrenadeEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import static cofh.thermal.core.init.TCoreReferences.EARTH_GRENADE_ENTITY;
import static cofh.thermal.core.init.TCoreReferences.EARTH_GRENADE_ITEM;

public class EarthGrenadeEntity extends AbstractGrenadeEntity {

    public EarthGrenadeEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public EarthGrenadeEntity(World worldIn, double x, double y, double z) {

        super(EARTH_GRENADE_ENTITY, x, y, z, worldIn);
    }

    public EarthGrenadeEntity(World worldIn, LivingEntity livingEntityIn) {

        super(EARTH_GRENADE_ENTITY, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {

        return EARTH_GRENADE_ITEM;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

    }

}
