package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.FrostArrowEntity;
import cofh.lib.item.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FrostArrowItem extends ArrowItemCoFH {

    public FrostArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new FrostArrowEntity(worldIn, shooter);
    }

}
