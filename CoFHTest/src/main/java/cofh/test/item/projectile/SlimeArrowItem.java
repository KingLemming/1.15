package cofh.test.item.projectile;

import cofh.lib.item.override.ArrowItemCoFH;
import cofh.test.entity.projectile.SlimeArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SlimeArrowItem extends ArrowItemCoFH {

    public SlimeArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new SlimeArrowEntity(worldIn, shooter);
    }

}
