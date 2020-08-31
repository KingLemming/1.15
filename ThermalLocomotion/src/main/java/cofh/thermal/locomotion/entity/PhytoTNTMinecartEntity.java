package cofh.thermal.locomotion.entity;

import cofh.core.entity.AbstractTNTMinecartEntity;
import cofh.core.util.Utils;
import cofh.thermal.core.item.PhytoGroItem;
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
import static cofh.thermal.core.init.TCoreIDs.ID_PHYTO_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.PHYTO_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.PHYTO_TNT_CART_ITEM;

public class PhytoTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public PhytoTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public PhytoTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(PHYTO_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return detonated ? Blocks.AIR : BLOCKS.get(ID_PHYTO_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(PHYTO_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(world)) {
            Utils.growPlants(this, world, this.getPosition(), radius);
            for (int i = 0; i < 4; ++i) {
                PhytoGroItem.growSeagrass(world, this.getPosition(), null);
            }
            makeAreaOfEffectCloud();
            this.remove();
            this.entityDropItem(getCartItem());
        }
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

    private void makeAreaOfEffectCloud() {

        AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(world, getPosX(), getPosY(), getPosZ());
        cloud.setRadius(1);
        cloud.setParticleData(ParticleTypes.HAPPY_VILLAGER);
        cloud.setDuration(CLOUD_DURATION);
        cloud.setWaitTime(0);
        cloud.setRadiusPerTick((radius - cloud.getRadius()) / (float) cloud.getDuration());

        world.addEntity(cloud);
    }

}
