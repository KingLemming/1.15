package cofh.thermal.locomotion.entity;

import cofh.core.entity.AbstractTNTMinecartEntity;
import cofh.core.util.Utils;
import cofh.thermal.core.entity.projectile.EarthGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.TCoreIDs.ID_EARTH_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.EARTH_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.EARTH_TNT_CART_ITEM;

public class EarthTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public EarthTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public EarthTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(EARTH_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return detonated ? Blocks.AIR : BLOCKS.get(ID_EARTH_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.MINECART) : new ItemStack(EARTH_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(world)) {
            EarthGrenadeEntity.sunderNearbyEntities(this, world, this.getPosition(), radius);
            EarthGrenadeEntity.breakBlocks(this, world, this.getPosition(), radius - 1, null);
            this.remove();
            this.entityDropItem(getCartItem());
        }
        this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

}
