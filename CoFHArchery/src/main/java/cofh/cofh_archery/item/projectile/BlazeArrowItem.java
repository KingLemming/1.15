package cofh.cofh_archery.item.projectile;

import cofh.cofh_archery.entity.projectile.BlazeArrowEntity;
import cofh.lib.item.override.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlazeArrowItem extends ArrowItemCoFH {

    public BlazeArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new BlazeArrowEntity(worldIn, shooter);
    }

}
