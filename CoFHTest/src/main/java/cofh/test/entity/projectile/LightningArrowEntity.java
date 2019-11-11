package cofh.test.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.test.CoFHTest.EXPLOSIVE_ARROW_ENTITY;
import static cofh.test.CoFHTest.EXPLOSIVE_ARROW_ITEM;

public class LightningArrowEntity extends AbstractArrowEntity {

    public LightningArrowEntity(EntityType<? extends LightningArrowEntity> p_i50158_1_, World p_i50158_2_) {

        super(p_i50158_1_, p_i50158_2_);
    }

    public LightningArrowEntity(World worldIn, LivingEntity shooter) {

        super(EXPLOSIVE_ARROW_ENTITY.get(), shooter, worldIn);
    }

    public LightningArrowEntity(World worldIn, double x, double y, double z) {

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
                world.createExplosion(this, posX, posY, posZ, 1.0F, Explosion.Mode.NONE);
                this.remove();
            }
        }
    }

    //    @Override
    //    protected void func_213868_a(EntityRayTraceResult rayTraceResultIn) {
    //
    //        super.func_213868_a(rayTraceResultIn);
    //
    //        world.createExplosion(this, posX, posY, posZ, 1.0F, Explosion.Mode.NONE);
    //        this.remove();
    //    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
