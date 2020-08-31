package cofh.archersparadox.entity.projectile;

import cofh.core.util.Utils;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.archersparadox.init.APReferences.MAGMA_ARROW_ENTITY;
import static cofh.core.util.constants.NBTTags.TAG_ARROW_DATA;

public class MagmaArrowEntity extends AbstractArrowEntity {

    private static float DAMAGE = 0.5F;
    private static final int DURATION = 5;
    private static int KNOCKBACK = 3;
    private static int KNOCKBACK_FACTOR = 1;
    private static float MAX_VELOCITY = 3.0F;
    private static float MIN_VELOCITY = 0.5F;
    private static final int RADIUS = 2;

    private int bounces = 0;
    private int maxBounces = KNOCKBACK;

    public MagmaArrowEntity(EntityType<? extends MagmaArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
        this.knockbackStrength = KNOCKBACK;
    }

    public MagmaArrowEntity(World worldIn, LivingEntity shooter) {

        super(MAGMA_ARROW_ENTITY, shooter, worldIn);
        this.damage = DAMAGE;
        this.knockbackStrength = KNOCKBACK;
    }

    public MagmaArrowEntity(World worldIn, double x, double y, double z) {

        super(MAGMA_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = DAMAGE;
        this.knockbackStrength = KNOCKBACK;
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(Items.ARROW);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        if (raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            this.setHitSound(SoundEvents.ENTITY_MAGMA_CUBE_SQUISH);
            Utils.igniteNearbyEntities(this, world, this.getPosition(), RADIUS, DURATION);
            Utils.igniteNearbyGround(this, world, this.getPosition(), RADIUS, 0.1);

            if (raytraceResultIn.getType() == RayTraceResult.Type.ENTITY) {
                this.onEntityHit((EntityRayTraceResult) raytraceResultIn);
            } else if (raytraceResultIn.getType() == RayTraceResult.Type.BLOCK) {
                Vec3d motion = getMotion();
                if (motion.lengthSquared() < MIN_VELOCITY || isInWater() || bounces >= maxBounces) {
                    super.onHit(raytraceResultIn);
                    return;
                }
                BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceResultIn;
                switch (blockraytraceresult.getFace()) {
                    case DOWN:
                    case UP:
                        this.setMotion(motion.x, motion.y * -1, motion.z);
                        break;
                    case NORTH:
                    case SOUTH:
                        this.setMotion(motion.x, motion.y, motion.z * -1);
                        break;
                    case WEST:
                    case EAST:
                        this.setMotion(motion.x * -1, motion.y, motion.z);
                        break;
                }
                float f = MathHelper.sqrt(horizontalMag(motion));
                this.rotationYaw = (float) (MathHelper.atan2(motion.x, motion.z) * (double) (180F / (float) Math.PI));
                this.rotationPitch = (float) (MathHelper.atan2(motion.y, f) * (double) (180F / (float) Math.PI));
                this.prevRotationYaw = this.rotationYaw;
                this.prevRotationPitch = this.rotationPitch;
                ++bounces;
                --knockbackStrength;
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult raytraceResultIn) {

        super.onEntityHit(raytraceResultIn);

        Entity entity = raytraceResultIn.getEntity();
        if (!isInWater() && !(entity instanceof EndermanEntity)) {
            entity.setFire(DURATION);
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

        super.setKnockbackStrength(KNOCKBACK + knockbackStrengthIn * KNOCKBACK_FACTOR);
        this.maxBounces = this.knockbackStrength;
    }

    @Override
    public void setPierceLevel(byte level) {

    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {

        super.shoot(x, y, z, Math.min(velocity, MAX_VELOCITY), inaccuracy);
    }

    @Override
    public void tick() {

        super.tick();

        if (!this.inGround || this.getNoClip()) {
            if (Utils.isClientWorld(world)) {
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
        compound.putInt(TAG_ARROW_DATA, bounces);

    }

    @Override
    public void readAdditional(CompoundNBT compound) {

        super.readAdditional(compound);
        bounces = compound.getInt(TAG_ARROW_DATA);
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
