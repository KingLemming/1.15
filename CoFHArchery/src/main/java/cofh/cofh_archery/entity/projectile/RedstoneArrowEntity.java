package cofh.cofh_archery.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.REDSTONE_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.REDSTONE_ARROW_ITEM;

public class RedstoneArrowEntity extends AbstractArrowEntity {

    public RedstoneArrowEntity(EntityType<? extends RedstoneArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
    }

    public RedstoneArrowEntity(World worldIn, LivingEntity shooter) {

        super(REDSTONE_ARROW_ENTITY.get(), shooter, worldIn);
    }

    public RedstoneArrowEntity(World worldIn, double x, double y, double z) {

        super(REDSTONE_ARROW_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(REDSTONE_ARROW_ITEM.get());
    }

    //    @Override
    //    protected void onHit(RayTraceResult raytraceResultIn) {
    //
    //        super.onHit(raytraceResultIn);
    //        if (raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
    //            if (!isInWater() && !isInLava()) {
    //                world.createExplosion(this, posX, posY, posZ, 2.0F, isBurning(), Explosion.Mode.NONE);
    //                this.remove();
    //            }
    //        }
    //    }

    public void tick() {

        super.tick();

        if (inGround) {
            System.out.println("tick");
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
