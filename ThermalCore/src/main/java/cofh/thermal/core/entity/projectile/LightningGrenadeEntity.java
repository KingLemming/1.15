package cofh.thermal.core.entity.projectile;

import cofh.lib.entity.AbstractGrenadeEntity;
import cofh.lib.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import static cofh.lib.util.references.CoreReferences.SHOCKED;
import static cofh.thermal.core.init.TCoreReferences.LIGHTNING_GRENADE_ENTITY;
import static cofh.thermal.core.init.TCoreReferences.LIGHTNING_GRENADE_ITEM;

public class LightningGrenadeEntity extends AbstractGrenadeEntity {

    public static int effectAmplifier = 1;
    public static int effectDuration = 200;

    public LightningGrenadeEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {

        super(type, worldIn);
    }

    public LightningGrenadeEntity(World worldIn, double x, double y, double z) {

        super(LIGHTNING_GRENADE_ENTITY, x, y, z, worldIn);
    }

    public LightningGrenadeEntity(World worldIn, LivingEntity livingEntityIn) {

        super(LIGHTNING_GRENADE_ENTITY, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {

        return LIGHTNING_GRENADE_ITEM;
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (Utils.isServerWorld(world)) {
            BlockPos pos = this.getPosition();
            if (this.world.canSeeSky(pos)) {
                LightningBoltEntity bolt = new LightningBoltEntity(this.world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, false);
                bolt.setCaster(getThrower() instanceof ServerPlayerEntity ? (ServerPlayerEntity) getThrower() : null);
                ((ServerWorld) this.world).addLightningBolt(bolt);
            }
            shockNearbyEntities(this, world, this.getPosition(), radius);
            Utils.zapNearbyGround(this, world, this.getPosition(), radius, 0.05, 3);
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
        if (result.getType() == RayTraceResult.Type.ENTITY && this.ticksExisted < 10) {
            return;
        }
        this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 0.5F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

    public static void shockNearbyEntities(Entity entity, World worldIn, BlockPos pos, int radius) {

        AxisAlignedBB area = new AxisAlignedBB(pos.add(-radius, -radius, -radius), pos.add(1 + radius, 1 + radius, 1 + radius));
        worldIn.getEntitiesWithinAABB(LivingEntity.class, area, EntityPredicates.IS_ALIVE)
                .forEach(livingEntity -> livingEntity.addPotionEffect(new EffectInstance(SHOCKED, effectDuration, effectAmplifier, false, false)));
    }

}
