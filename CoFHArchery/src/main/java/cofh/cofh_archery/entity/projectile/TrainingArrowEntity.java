package cofh.cofh_archery.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.TRAINING_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.TRAINING_ARROW_ITEM;

public class TrainingArrowEntity extends AbstractArrowEntity {

    public static float DAMAGE = 0.0F;

    public TrainingArrowEntity(EntityType<? extends TrainingArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
    }

    public TrainingArrowEntity(World worldIn, LivingEntity shooter) {

        super(TRAINING_ARROW_ENTITY.get(), shooter, worldIn);
        this.damage = DAMAGE;

    }

    public TrainingArrowEntity(World worldIn, double x, double y, double z) {

        super(TRAINING_ARROW_ENTITY.get(), x, y, z, worldIn);
        this.damage = DAMAGE;
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(TRAINING_ARROW_ITEM.get());
    }

    @Override
    protected void func_213868_a(EntityRayTraceResult raytraceResultIn) {

        if (!this.world.isRemote) {
            if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
                this.entityDropItem(this.getArrowStack(), 0.1F);
            }
            this.remove();
        }
        this.playSound(SoundEvents.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
    }

    @Override
    public void setFire(int seconds) {

    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

    }

    @Override
    public void setPierceLevel(byte level) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
