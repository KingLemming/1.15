package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;

public class BulwarkEnchantment extends EnchantmentCoFH {

    public BulwarkEnchantment(String id) {

        super(id, Rarity.COMMON, EnchantmentType.BREAKABLE, new EquipmentSlotType[]{EquipmentSlotType.OFFHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1 + (level - 1) * 5;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable && (stack.getItem().isShield(stack, null) || supportsEnchantment(stack));
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Bulwark");

        String comment = "If TRUE, the Bulwark Enchantment is available for Shields.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
    }
    // endregion
}
