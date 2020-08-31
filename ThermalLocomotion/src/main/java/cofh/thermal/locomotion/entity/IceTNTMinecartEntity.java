package cofh.thermal.locomotion.entity;

import cofh.core.entity.AbstractTNTMinecartEntity;
import cofh.core.util.Utils;
import cofh.thermal.core.entity.projectile.IceGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.common.ThermalConfig.permanentLava;
import static cofh.thermal.core.common.ThermalConfig.permanentWater;
import static cofh.thermal.core.init.TCoreIDs.ID_ICE_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.ICE_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.ICE_TNT_CART_ITEM;

public class IceTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public IceTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public IceTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(ICE_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return detonated ? Blocks.AIR : BLOCKS.get(ID_ICE_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(ICE_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(world)) {
            IceGrenadeEntity.chillNearbyEntities(this, world, this.getPosition(), radius);
            Utils.freezeSpecial(this, world, this.getPosition(), radius, true, true);
            Utils.freezeNearbyGround(this, world, this.getPosition(), radius);
            Utils.freezeAllWater(this, world, this.getPosition(), radius, permanentWater);
            Utils.freezeAllLava(this, world, this.getPosition(), radius, permanentLava);
            makeAreaOfEffectCloud();
            this.remove();
            this.entityDropItem(getCartItem());
        }
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY() + 0.5D, getPosZ());
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.ITEM_SNOWBALL);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((radius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
