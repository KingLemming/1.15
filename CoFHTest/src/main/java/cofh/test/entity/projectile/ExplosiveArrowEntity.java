package cofh.test.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.test.CoFHTest.EXPLOSIVE_ARROW_ENTITY;
import static cofh.test.CoFHTest.EXPLOSIVE_ARROW_ITEM;

public class ExplosiveArrowEntity extends AbstractArrowEntity {

    public ExplosiveArrowEntity(EntityType<? extends ExplosiveArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
    }

    public ExplosiveArrowEntity(World worldIn, LivingEntity shooter) {

        super(EXPLOSIVE_ARROW_ENTITY.get(), shooter, worldIn);
    }

    public ExplosiveArrowEntity(World worldIn, double x, double y, double z) {

        super(EXPLOSIVE_ARROW_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(EXPLOSIVE_ARROW_ITEM.get());
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);
        if (raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            if (!isInWater() && !isInLava()) {
                world.createExplosion(this, posX, posY, posZ, 2.0F, isBurning(), Explosion.Mode.NONE);
                this.remove();
            }
        }
    }

    @Override
    protected void func_213868_a(EntityRayTraceResult raytraceResultIn) {

        Entity entity = raytraceResultIn.getEntity();
        if (this.isBurning() && !(entity instanceof EndermanEntity)) {
            entity.setFire(5);
        }
        if (isInWater()) {
            entityDropItem(getArrowStack(), 0.1F);
            this.remove();
        }
    }

    @Override
    public void setIsCritical(boolean critical) {

    }

    @Override
    public void setPierceLevel(byte level) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
