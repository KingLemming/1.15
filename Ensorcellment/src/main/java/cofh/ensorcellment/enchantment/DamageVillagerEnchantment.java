package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.DamageEnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public class DamageVillagerEnchantment extends DamageEnchantmentCoFH {

    public static boolean enableEmeraldDrops = true;

    public DamageVillagerEnchantment() {

        super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 5;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 8;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 20;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || item instanceof CrossbowItem || supportsEnchantment(stack));
    }

    public static boolean validTarget(Entity entity) {

        return entity instanceof AbstractVillagerEntity || entity instanceof IronGolemEntity;
    }

}