package cofh.cofh_archery.entity.projectile;

import cofh.lib.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.ENDER_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.ENDER_ARROW_ITEM;
import static cofh.lib.util.constants.Tags.TAG_ARROW_DATA;
import static cofh.lib.util.references.CoreReferences.ENDERFERENCE;

public class EnderArrowEntity extends AbstractArrowEntity {

    private static float DAMAGE = 0.5F;
    private static final int DURATION = 80;
    private static final int DURATION_FACTOR = 2;

    public boolean discharged;
    private BlockPos origin;

    public EnderArrowEntity(EntityType<? extends EnderArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
    }

    public EnderArrowEntity(World worldIn, LivingEntity shooter) {

        super(ENDER_ARROW_ENTITY.get(), shooter, worldIn);
        this.damage = DAMAGE;
        this.origin = shooter.getPosition();
    }

    public EnderArrowEntity(World worldIn, double x, double y, double z) {

        super(ENDER_ARROW_ENTITY.get(), x, y, z, worldIn);
        this.damage = DAMAGE;
        this.origin = new BlockPos(x, y, z);
    }

    @Override
    protected ItemStack getArrowStack() {

        return discharged ? new ItemStack(Items.ARROW) : new ItemStack(ENDER_ARROW_ITEM.get());
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {

        super.onHit(raytraceResultIn);

        Entity shooter = getShooter();
        if (raytraceResultIn.getType() != RayTraceResult.Type.MISS && !discharged && shooter != null) {
            int duration = Math.max(DURATION, ticksInAir * DURATION_FACTOR);
            if (raytraceResultIn.getType() == RayTraceResult.Type.BLOCK) {
                Utils.teleportEntityTo(shooter, this.getPosition());
                if (shooter instanceof LivingEntity && !Utils.isFakePlayer(shooter)) {
                    ((LivingEntity) shooter).addPotionEffect(new EffectInstance(ENDERFERENCE, duration, 0, false, false));
                }
            }
            if (raytraceResultIn.getType() == RayTraceResult.Type.ENTITY) {
                BlockPos originPos = origin == null ? shooter.getPosition() : origin;
                Utils.teleportEntityTo(shooter, getPosition());
                if (shooter instanceof LivingEntity && !Utils.isFakePlayer(shooter)) {
                    ((LivingEntity) shooter).addPotionEffect(new EffectInstance(ENDERFERENCE, duration, 0, false, false));
                }
                Entity entity = ((EntityRayTraceResult) raytraceResultIn).getEntity();
                if (entity instanceof LivingEntity) {
                    Utils.teleportEntityTo(entity, originPos);
                    ((LivingEntity) entity).addPotionEffect(new EffectInstance(ENDERFERENCE, duration, 0, false, false));
                }
            }
            discharged = true;
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

        super.tick();

        if (!this.inGround || this.func_203047_q()) {
            if (Utils.isClientWorld(world)) {
                Vec3d vec3d = this.getMotion();
                double d1 = vec3d.x;
                double d2 = vec3d.y;
                double d0 = vec3d.z;
                this.world.addParticle(ParticleTypes.PORTAL, this.posX + d1 * 0.25D, this.posY + d2 * 0.25D, this.posZ + d0 * 0.25D, -d1, -d2 + 0.2D, -d0);
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

}
