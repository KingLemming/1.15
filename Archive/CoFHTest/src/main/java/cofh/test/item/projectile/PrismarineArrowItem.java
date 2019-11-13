package cofh.potions.item.projectile;

import cofh.lib.item.override.ArrowItemCoFH;
import cofh.potions.entity.projectile.PrismarineArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PrismarineArrowItem extends ArrowItemCoFH {

    public PrismarineArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new PrismarineArrowEntity(worldIn, shooter);
    }

}
