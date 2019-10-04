package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.enchantment.EnchantmentOverride;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

import static cofh.lib.util.constants.Constants.ID_ENSORCELLMENT;

public class MendingEnchantmentAlt extends EnchantmentOverride {

    public static float anvilDamage = 0.03F;

    public MendingEnchantmentAlt() {

        super(Rarity.RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        name = "enchantment." + ID_ENSORCELLMENT + ".preservation";
    }

    public EnchantmentCoFH setEnable(boolean enable) {

        this.enable = enable;
        name = "enchantment." + (enable ? ID_ENSORCELLMENT + ".preservation" : "minecraft.mending");
        return this;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 25;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

}
