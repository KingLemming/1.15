package cofh.thermal.core.entity.item;

import cofh.lib.util.Utils;
import cofh.thermal.core.item.FertilizerItem;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.thermal.core.init.TCoreReferences.PNT_ENTITY;

public class PNTEntity extends Entity {

    private static final DataParameter<Integer> FUSE = EntityDataManager.createKey(PNTEntity.class, DataSerializers.VARINT);
    private static final int CLOUD_DURATION = 20;
    private static final int RADIUS = 9;

    private int fuse = 80;

    public PNTEntity(EntityType<? extends PNTEntity> type, World worldIn) {

        super(type, worldIn);
        this.preventEntitySpawning = true;
    }

    public PNTEntity(World worldIn, double x, double y, double z) {

        this(PNT_ENTITY, worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double) ((float) Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    protected void registerData() {

        this.dataManager.register(FUSE, 80);
    }

    @Override
    protected boolean canTriggerWalking() {

        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {

        return isAlive();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {

        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
        }
        this.move(MoverType.SELF, this.getMotion());
        this.setMotion(this.getMotion().scale(0.98D));
        if (this.onGround) {
            this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
        }
        --this.fuse;
        if (this.fuse <= 0) {
            this.remove();
            this.explode();
        } else {
            this.handleWaterMovement();
            if (this.world.isRemote) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void explode() {

        if (Utils.isServerWorld(world)) {
            Utils.growPlants(this, world, this.getPosition(), RADIUS);
            for (int i = 0; i < 4; ++i) {
                FertilizerItem.growSeagrass(world, this.getPosition(), null);
            }
            makeAreaOfEffectCloud();
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 0.5F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

        compound.putShort("Fuse", (short) this.getFuse());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

        this.setFuse(compound.getShort("Fuse"));
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {

        return 0.0F;
    }

    public void setFuse(int fuseIn) {

        this.dataManager.set(FUSE, fuseIn);
        this.fuse = fuseIn;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {

        if (FUSE.equals(key)) {
            this.fuse = this.getFuseDataManager();
        }
    }

    public int getFuseDataManager() {

        return this.dataManager.get(FUSE);
    }

    public int getFuse() {

        return this.fuse;
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
        cloud.setRadiusPerTick((RADIUS - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
