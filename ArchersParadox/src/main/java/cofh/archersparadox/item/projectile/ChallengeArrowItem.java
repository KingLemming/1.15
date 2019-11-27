package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.ChallengeArrowEntity;
import cofh.lib.item.override.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ChallengeArrowItem extends ArrowItemCoFH {

    public ChallengeArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new ChallengeArrowEntity(worldIn, shooter);
    }

}
