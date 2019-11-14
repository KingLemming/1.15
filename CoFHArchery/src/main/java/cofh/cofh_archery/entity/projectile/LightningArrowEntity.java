package cofh.cofh_archery.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.LIGHTNING_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.LIGHTNING_ARROW_ITEM;
import static cofh.lib.util.constants.Tags.TAG_ARROW_DATA;

public class LightningArrowEntity extends AbstractArrowEntity {

    private static float DAMAGE = 1.5F;

    public boolean discharged;

    public LightningArrowEntity(EntityType<? extends LightningArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
    }

    public LightningArrowEntity(World worldIn, LivingEntity shooter) {

        super(LIGHTNING_ARROW_ENTITY.get(), shooter, worldIn);
        this.damage = DAMAGE;
    }

    public LightningArrowEntity(World worldIn, double x, double y, double z) {

        super(LIGHTNING_ARROW_ENTITY.get(), x, y, z, worldIn);
        this.damage = DAMAGE;
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(Items.ARROW) : new ItemStack(LIGHTNING_ARROW_ITEM.get());
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);

        if (!discharged && raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            if (!isInWater() && !isInLava()) {
                BlockPos pos = this.getPosition();
                if (this.world.isSkyLightMax(pos)) {
                    discharged = true;
                    if (this.world instanceof ServerWorld) {
                        LightningBoltEntity bolt = new LightningBoltEntity(this.world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, false);
                        bolt.setCaster(getShooter() instanceof ServerPlayerEntity ? (ServerPlayerEntity) getShooter() : null);
                        ((ServerWorld) this.world).addLightningBolt(bolt);
                    }
                }
            }
        }
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

}
