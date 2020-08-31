package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.RedstoneArrowEntity;
import cofh.core.item.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RedstoneArrowItem extends ArrowItemCoFH {

    public RedstoneArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new RedstoneArrowEntity(worldIn, shooter);
    }

}
