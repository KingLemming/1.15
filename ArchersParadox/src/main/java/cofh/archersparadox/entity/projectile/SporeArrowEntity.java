package cofh.archersparadox.entity.projectile;

import cofh.lib.util.Utils;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

import static cofh.archersparadox.init.ModReferences.SPORE_ARROW_ENTITY;
import static cofh.archersparadox.init.ModReferences.SPORE_ARROW_ITEM;
import static cofh.lib.util.constants.Tags.TAG_ARROW_DATA;

public class SporeArrowEntity extends AbstractArrowEntity {

    private static float DAMAGE = 0.5F;
    private static final int CLOUD_DURATION = 20;

    public static int radius = 4;
    public static boolean transform = true;

    public boolean discharged;

    public SporeArrowEntity(EntityType<? extends SporeArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
    }

    public SporeArrowEntity(World worldIn, LivingEntity shooter) {

        super(SPORE_ARROW_ENTITY, shooter, worldIn);
        this.damage = DAMAGE;
    }

    public SporeArrowEntity(World worldIn, double x, double y, double z) {

        super(SPORE_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = DAMAGE;
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(Items.ARROW) : new ItemStack(SPORE_ARROW_ITEM);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);

        if (!discharged && raytraceResultIn.getType() != RayTraceResult.Type.MISS) {
            Utils.transformMycelium(this, world, this.getPosition(), radius);
            makeAreaOfEffectCloud();
            discharged = true;
        }
    }

    @Override
    protected void func_213868_a(EntityRayTraceResult raytraceResultIn) {

        super.func_213868_a(raytraceResultIn);

        //        Entity entity = raytraceResultIn.getEntity();
        //        if (!entity.isInvulnerable() && entity instanceof CowEntity) {
        //
        //        }
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

        if (!this.inGround || this.func_203047_q()) {
            if (Utils.isClientWorld(world)) {
                Vec3d vec3d = this.getMotion();
                double d1 = vec3d.x;
                double d2 = vec3d.y;
                double d0 = vec3d.z;
                for (int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.MYCELIUM, this.posX + d1 * (double) i / 4.0D, this.posY + d2 * (double) i / 4.0D, this.posZ + d0 * (double) i / 4.0D, -d1, -d2 + 0.2D, -d0);
                }
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
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private void makeAreaOfEffectCloud() {

        if (Utils.isClientWorld(world)) {
            return;
        }
        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, posX, posY, posZ);
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.MYCELIUM);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((radius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
