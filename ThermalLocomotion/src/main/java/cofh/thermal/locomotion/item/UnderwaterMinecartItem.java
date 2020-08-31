package cofh.thermal.locomotion.item;

import cofh.core.item.MinecartItemCoFH;
import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;

public class UnderwaterMinecartItem extends MinecartItemCoFH {

    public UnderwaterMinecartItem(Properties builder) {

        super(UnderwaterMinecartEntity::new, builder);
        setEnchantability(1);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return enchantment == Enchantments.RESPIRATION;
    }

}
