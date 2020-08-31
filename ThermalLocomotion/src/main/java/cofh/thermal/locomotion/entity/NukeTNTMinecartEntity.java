package cofh.thermal.locomotion.entity;

import cofh.core.entity.AbstractTNTMinecartEntity;
import cofh.core.util.Utils;
import cofh.thermal.core.entity.projectile.NukeGrenadeEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.init.TCoreIDs.ID_NUKE_TNT;
import static cofh.thermal.locomotion.init.TLocReferences.NUKE_TNT_CART_ENTITY;
import static cofh.thermal.locomotion.init.TLocReferences.NUKE_TNT_CART_ITEM;

public class NukeTNTMinecartEntity extends AbstractTNTMinecartEntity {

    public NukeTNTMinecartEntity(EntityType<?> type, World worldIn) {

        super(type, worldIn);
    }

    public NukeTNTMinecartEntity(World worldIn, double posX, double posY, double posZ) {

        super(NUKE_TNT_CART_ENTITY, worldIn, posX, posY, posZ);
    }

    @Override
    public Block getBlock() {

        return detonated ? Blocks.AIR : BLOCKS.get(ID_NUKE_TNT);
    }

    @Override
    public ItemStack getCartItem() {

        return detonated ? new ItemStack(Items.AIR) : new ItemStack(NUKE_TNT_CART_ITEM);
    }

    @Override
    protected void explode() {

        if (Utils.isServerWorld(world)) {
            world.setBlockState(this.getPosition(), Blocks.AIR.getDefaultState());
            NukeGrenadeEntity.damageNearbyEntities(this, world, this.getPosition(), radius * 2, null);
            NukeGrenadeEntity.destroyBlocks(this, world, this.getPosition(), radius + radius / 2);
            world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float) NukeGrenadeEntity.explosionStrength * 2, !this.isInWater(), NukeGrenadeEntity.explosionsBreakBlocks ? Explosion.Mode.BREAK : Explosion.Mode.NONE);
            this.remove();
        }
        this.world.addParticle(ParticleTypes.FLASH, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 2.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F, false);
    }

}
