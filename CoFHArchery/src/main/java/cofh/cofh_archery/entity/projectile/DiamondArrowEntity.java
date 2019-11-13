package cofh.cofh_archery.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.DIAMOND_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.DIAMOND_ARROW_ITEM;

public class DiamondArrowEntity extends AbstractArrowEntity {

    private static float DAMAGE = 4.0F;
    private static int KNOCKBACK = 1;
    private static int PIERCE = 1;

    public DiamondArrowEntity(EntityType<? extends DiamondArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = DAMAGE;
    }

    public DiamondArrowEntity(World worldIn, LivingEntity shooter) {

        super(DIAMOND_ARROW_ENTITY.get(), shooter, worldIn);
        this.damage = DAMAGE;
    }

    public DiamondArrowEntity(World worldIn, double x, double y, double z) {

        super(DIAMOND_ARROW_ENTITY.get(), x, y, z, worldIn);
        this.damage = DAMAGE;
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(DIAMOND_ARROW_ITEM.get());
    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

        super.setKnockbackStrength(KNOCKBACK + knockbackStrengthIn);
    }

    @Override
    public void setPierceLevel(byte level) {

        super.setPierceLevel((byte) (PIERCE + level));
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
