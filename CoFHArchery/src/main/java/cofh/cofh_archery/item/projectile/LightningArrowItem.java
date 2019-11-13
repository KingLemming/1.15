package cofh.cofh_archery.item.projectile;

import cofh.cofh_archery.entity.projectile.LightningArrowEntity;
import cofh.lib.item.override.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LightningArrowItem extends ArrowItemCoFH {

    public LightningArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new LightningArrowEntity(worldIn, shooter);
    }

}
