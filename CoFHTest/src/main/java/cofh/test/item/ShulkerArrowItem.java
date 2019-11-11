package cofh.test.item;

import cofh.lib.item.override.ArrowItemCoFH;
import cofh.test.entity.projectile.ShulkerArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShulkerArrowItem extends ArrowItemCoFH {

    public ShulkerArrowItem(Properties builder) {

        super(builder);
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new ShulkerArrowEntity(worldIn, shooter);
    }

}
