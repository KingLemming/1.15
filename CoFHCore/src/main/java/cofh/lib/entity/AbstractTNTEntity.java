package cofh.lib.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import static cofh.lib.util.constants.NBTTags.TAG_FUSE;

public abstract class AbstractTNTEntity extends Entity {

    protected static final DataParameter<Integer> FUSE = EntityDataManager.createKey(AbstractTNTEntity.class, DataSerializers.VARINT);
    protected static final int CLOUD_DURATION = 20;

    @Nullable
    protected LivingEntity igniter;
    protected int fuse = 80;
    protected int radius = 9;

    public AbstractTNTEntity(EntityType<? extends AbstractTNTEntity> type, World worldIn) {

        super(type, worldIn);
        this.preventEntitySpawning = true;
    }

    public AbstractTNTEntity(EntityType<? extends AbstractTNTEntity> type, World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {

        this(type, worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double) ((float) Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.igniter = igniter;
    }

    @Override
    protected void registerData() {

        this.dataManager.register(FUSE, 80);
    }

    @Override
    protected boolean canTriggerWalking() {

        return false;
    }

    @Override
    public boolean canBeCollidedWith() {

        return isAlive();
    }

    @Override
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

    @Override
    protected void writeAdditional(CompoundNBT compound) {

        compound.putShort(TAG_FUSE, (short) this.getFuse());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

        this.setFuse(compound.getShort(TAG_FUSE));
    }

    @Override
    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {

        return 0.0F;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {

        if (FUSE.equals(key)) {
            this.fuse = this.getFuseDataManager();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setFuse(int fuseIn) {

        this.dataManager.set(FUSE, fuseIn);
        this.fuse = fuseIn;
    }

    public int getFuse() {

        return this.fuse;
    }

    public abstract Block getBlock();

    protected int getFuseDataManager() {

        return this.dataManager.get(FUSE);
    }

    protected abstract void explode();

}
