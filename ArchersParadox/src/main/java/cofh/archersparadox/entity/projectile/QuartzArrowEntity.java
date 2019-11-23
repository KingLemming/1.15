package cofh.archersparadox.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.archersparadox.init.ModReferences.QUARTZ_ARROW_ENTITY;
import static cofh.archersparadox.init.ModReferences.QUARTZ_ARROW_ITEM;

public class QuartzArrowEntity extends AbstractArrowEntity {

    public static float attrDamage = 2.5F;
    public static int attrKnockback = 1;
    public static byte attrPierce = 0;

    public QuartzArrowEntity(EntityType<? extends QuartzArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        this.damage = attrDamage;
        setKnockbackStrength(0);
        setPierceLevel((byte) 0);
    }

    public QuartzArrowEntity(World worldIn, LivingEntity shooter) {

        super(QUARTZ_ARROW_ENTITY, shooter, worldIn);
        this.damage = attrDamage;
        setKnockbackStrength(0);
        setPierceLevel((byte) 0);
    }

    public QuartzArrowEntity(World worldIn, double x, double y, double z) {

        super(QUARTZ_ARROW_ENTITY, x, y, z, worldIn);
        this.damage = attrDamage;
        setKnockbackStrength(0);
        setPierceLevel((byte) 0);
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(QUARTZ_ARROW_ITEM);
    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

        super.setKnockbackStrength(attrKnockback + knockbackStrengthIn);
    }

    @Override
    public void setPierceLevel(byte level) {

        super.setPierceLevel((byte) (attrPierce + level));
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
