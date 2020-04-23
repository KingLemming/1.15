package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.DiamondArrowEntity;
import cofh.lib.item.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DiamondArrowItem extends ArrowItemCoFH {

    public DiamondArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new DiamondArrowEntity(worldIn, shooter);
    }

}
