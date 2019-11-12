package cofh.test.item.projectile;

import cofh.lib.item.override.ArrowItemCoFH;
import cofh.test.entity.projectile.TrainingArrowEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TrainingArrowItem extends ArrowItemCoFH {

    public TrainingArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new TrainingArrowEntity(worldIn, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {

        int enchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow);
        return enchant > 0;
    }

}
