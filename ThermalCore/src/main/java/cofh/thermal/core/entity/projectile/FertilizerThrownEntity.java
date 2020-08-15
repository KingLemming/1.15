package cofh.thermal.core.entity.projectile;

import cofh.lib.util.Utils;
import cofh.thermal.core.item.FertilizerItem;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.thermal.core.init.TCoreReferences.PHYTO_GRENADE_ENTITY;
import static cofh.thermal.core.init.TCoreReferences.PHYTO_GRENADE_ITEM;

public class FertilizerThrownEntity extends ProjectileItemEntity {

    private static final int CLOUD_DURATION = 20;

    public int radius = 5;

    public FertilizerThrownEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public FertilizerThrownEntity(World worldIn, LivingEntity livingEntityIn) {

        super(PHYTO_GRENADE_ENTITY, livingEntityIn, worldIn);
    }

    public FertilizerThrownEntity(World worldIn, double x, double y, double z) {

        super(PHYTO_GRENADE_ENTITY, x, y, z, worldIn);
    }

    public FertilizerThrownEntity setRadius(int radius) {

        this.radius = radius;
        return this;
    }

    @Override
    protected Item getDefaultItem() {

        return PHYTO_GRENADE_ITEM;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (Utils.isServerWorld(world)) {
            Utils.growPlants(this, world, this.getPosition(), radius);
            for (int i = 0; i < 2; ++i) {
                FertilizerItem.growSeagrass(world, this.getPosition(), null);
            }
            makeAreaOfEffectCloud();
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
        this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 0.5F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);

    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY(), getPosZ());
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.HAPPY_VILLAGER);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((radius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
