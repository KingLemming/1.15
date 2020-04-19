package cofh.archersparadox.entity.projectile;

import cofh.lib.util.Utils;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.archersparadox.init.APReferences.BLAZE_ARROW_ENTITY;
import static cofh.archersparadox.init.APReferences.BLAZE_ARROW_ITEM;
import static cofh.lib.util.constants.NBTTags.TAG_ARROW_DATA;

public class BlazeArrowEntity extends AbstractArrowEntity {

    private static int CLOUD_DURATION = 20;

    public static float baseDamage = 1.5F;
    public static int effectDuration = 10;
    public static int effectRadius = 2;

    public boolean discharged;

    public BlazeArrowEntity(EntityType<? extends BlazeArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = baseDamage;
    }

    public BlazeArrowEntity(World worldIn, LivingEntity shooter) {

        super(BLAZE_ARROW_ENTITY, shooter, worldIn);
        this.damage = baseDamage;
    }

    public BlazeArrowEntity(World worldIn, double x, double y, double z) {

        super(BLAZE_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = baseDamage;
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(Items.ARROW) : new ItemStack(BLAZE_ARROW_ITEM);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);

        if (!discharged && raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            if (effectRadius > 0 && !isInWater()) {
                if (Utils.isServerWorld(world)) {
                    if (effectDuration - 5 > 0) {
                        Utils.igniteNearbyEntities(this, world, this.getPosition(), effectRadius, effectDuration - 5);
                    }
                    Utils.igniteSpecial(this, world, this.getPosition(), effectRadius, true, true);
                    Utils.igniteNearbyGround(this, world, this.getPosition(), effectRadius, 0.1);
                    makeAreaOfEffectCloud();
                }
                discharged = true;
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult raytraceResultIn) {

        super.onEntityHit(raytraceResultIn);

        Entity entity = raytraceResultIn.getEntity();
        if (!entity.isInvulnerable() && !entity.isImmuneToFire() && !isInWater() && !(entity instanceof EndermanEntity)) {
            entity.setFire(effectDuration);
        }
    }

    @Override
    public void setFire(int seconds) {

    }

    @Override
    public void setIsCritical(boolean critical) {

    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

    }

    @Override
    public void setPierceLevel(byte level) {

    }

    @Override
    public void tick() {

        super.tick();

        if (!this.inGround || this.getNoClip()) {
            if (Utils.isClientWorld(world) && !isInWater()) {
                Vec3d vec3d = this.getMotion();
                double d1 = vec3d.x;
                double d2 = vec3d.y;
                double d0 = vec3d.z;
                this.world.addParticle(ParticleTypes.LAVA, this.getPosX() + d1 * 0.25D, this.getPosY() + d2 * 0.25D, this.getPosZ() + d0 * 0.25D, -d1, -d2 + 0.2D, -d0);
            }
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

        super.writeAdditional(compound);
        compound.putBoolean(TAG_ARROW_DATA, discharged);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {

        super.readAdditional(compound);
        discharged = compound.getBoolean(TAG_ARROW_DATA);
    }

    @Override
    public boolean isBurning() {

        return !this.world.isRemote;
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY(), getPosZ());
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.FLAME);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((effectRadius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
