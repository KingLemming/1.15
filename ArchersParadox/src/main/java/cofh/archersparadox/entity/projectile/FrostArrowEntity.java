package cofh.archersparadox.entity.projectile;

import cofh.core.util.AreaUtils;
import cofh.core.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.archersparadox.init.APReferences.FROST_ARROW_ENTITY;
import static cofh.archersparadox.init.APReferences.FROST_ARROW_ITEM;
import static cofh.core.util.constants.NBTTags.TAG_ARROW_DATA;
import static cofh.core.util.references.CoreReferences.CHILLED;

public class FrostArrowEntity extends AbstractArrowEntity {

    private static final int CLOUD_DURATION = 20;

    public static float baseDamage = 1.5F;
    public static int effectAmplifier = 1;
    public static int effectDuration = 100;
    public static int effectRadius = 4;
    public static boolean permanentLava = true;
    public static boolean permanentWater = true;

    public boolean discharged;

    public FrostArrowEntity(EntityType<? extends FrostArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = baseDamage;
    }

    public FrostArrowEntity(World worldIn, LivingEntity shooter) {

        super(FROST_ARROW_ENTITY, shooter, worldIn);
        this.damage = baseDamage;
    }

    public FrostArrowEntity(World worldIn, double x, double y, double z) {

        super(FROST_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = baseDamage;
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(Items.ARROW) : new ItemStack(FROST_ARROW_ITEM);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);

        if (!discharged && raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            if (effectRadius > 0) {
                if (Utils.isServerWorld(world)) {
                    AreaUtils.freezeNearbyGround(this, world, this.getPosition(), effectRadius);
                    AreaUtils.freezeSurfaceWater(this, world, this.getPosition(), effectRadius, permanentWater);
                    AreaUtils.freezeSurfaceLava(this, world, this.getPosition(), effectRadius, permanentLava);
                    makeAreaOfEffectCloud();
                }
                discharged = true;
            }
            if (inBlockState != null && inBlockState.getFluidState() != Fluids.EMPTY.getDefaultState()) {
                this.inGround = false;
                this.setMotion(getMotion().mul((this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F)));
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult raytraceResultIn) {

        super.onEntityHit(raytraceResultIn);

        Entity entity = raytraceResultIn.getEntity();
        if (entity.isBurning()) {
            entity.extinguish();
        }
        if (!entity.isInvulnerable() && entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entity;
            living.addPotionEffect(new EffectInstance(CHILLED, effectDuration, effectAmplifier, false, false));
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

        if (!this.world.isRemote) {
            this.setFlag(6, this.isGlowing());
        }
        this.baseTick();

        boolean flag = this.getNoClip();
        Vec3d vec3d = this.getMotion();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            this.rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180F / (float) Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
        BlockPos blockpos = new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());
        BlockState blockstate = this.world.getBlockState(blockpos);
        if (!blockstate.isAir(this.world, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
            if (!voxelshape.isEmpty()) {
                for (AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
                    if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ()))) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.isWet()) {
            this.extinguish();
        }
        if (this.inGround && !flag) {
            if (this.inBlockState != blockstate && this.world.hasNoCollisions(this.getBoundingBox().grow(0.06D))) {
                this.inGround = false;
                this.setMotion(vec3d.mul((this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F)));
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            } else if (!this.world.isRemote) {
                this.func_225516_i_();
            }
            ++this.timeInGround;
        } else {
            this.timeInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d1 = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
            Vec3d vec3d2 = vec3d1.add(vec3d);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, discharged ? RayTraceContext.FluidMode.NONE : RayTraceContext.FluidMode.SOURCE_ONLY, this));
            if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
                vec3d2 = raytraceresult.getHitVec();
            }
            while (this.isAlive()) {
                EntityRayTraceResult entityraytraceresult = this.rayTraceEntities(vec3d1, vec3d2);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }
                if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                    Entity entity = ((EntityRayTraceResult) raytraceresult).getEntity();
                    Entity entity1 = this.getShooter();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity) entity1).canAttackPlayer((PlayerEntity) entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }
                if (raytraceresult != null && !flag && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                    this.onHit(raytraceresult);
                    this.isAirBorne = true;
                }
                if (entityraytraceresult == null || this.getPierceLevel() <= 0) {
                    break;
                }
                raytraceresult = null;
            }
            vec3d = this.getMotion();
            double d1 = vec3d.x;
            double d2 = vec3d.y;
            double d0 = vec3d.z;
            //            if (this.getIsCritical()) {
            //                for (int i = 0; i < 4; ++i) {
            //                    this.world.addParticle(ParticleTypes.CRIT, this.getPosX() + d1 * (double) i / 4.0D, this.getPosY() + d2 * (double) i / 4.0D, this.getPosZ() + d0 * (double) i / 4.0D, -d1, -d2 + 0.2D, -d0);
            //                }
            //            }
            this.world.addParticle(ParticleTypes.ITEM_SNOWBALL, this.getPosX() + d1 * 0.25D, this.getPosY() + d2 * 0.25D, this.getPosZ() + d0 * 0.25D, -d1, -d2 + 0.2D, -d0);

            this.setRawPosition(this.getPosX() + d1, this.getPosY() + d2, this.getPosZ() + d0);
            float f4 = MathHelper.sqrt(horizontalMag(vec3d));
            if (flag) {
                this.rotationYaw = (float) (MathHelper.atan2(-d1, -d0) * (double) (180F / (float) Math.PI));
            } else {
                this.rotationYaw = (float) (MathHelper.atan2(d1, d0) * (double) (180F / (float) Math.PI));
            }
            this.rotationPitch = (float) (MathHelper.atan2(d2, f4) * (double) (180F / (float) Math.PI));
            while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
                this.prevRotationPitch -= 360.0F;
            }
            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }
            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }
            this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
            this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
            float f1 = 0.99F;
            if (this.isInWater()) {
                for (int j = 0; j < 4; ++j) {
                    this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - d1 * 0.25D, this.getPosY() - d2 * 0.25D, this.getPosZ() - d0 * 0.25D, d1, d2, d0);
                }
                f1 = this.getWaterDrag();
            }
            this.setMotion(vec3d.scale(f1));
            if (!this.hasNoGravity() && !flag) {
                Vec3d vec3d3 = this.getMotion();
                this.setMotion(vec3d3.x, vec3d3.y - 0.05D, vec3d3.z);
            }
            this.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
            this.doBlockCollisions();
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
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY(), getPosZ());
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.ITEM_SNOWBALL);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((effectRadius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
