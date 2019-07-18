package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;

public class AnglerEnchantment extends EnchantmentCoFH {

    public static int chance = 50;

    public AnglerEnchantment(String id) {

        super(id, Rarity.VERY_RARE, EnchantmentType.FISHING_ROD, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {

        return 10 + (enchantmentLevel - 1) * 9;
    }

    public int getMaxEnchantability(int enchantmentLevel) {

        return this.getMinEnchantability(enchantmentLevel) + 15;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof FishingRodItem || supportsEnchantment(stack));
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;
    private ForgeConfigSpec.IntValue cfgLevel;
    private ForgeConfigSpec.IntValue cfgChance;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Angler's Bounty");

        String comment = "If TRUE, the Angler's Bounty Enchantment is available for Fishing Rods.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        comment = "Adjust this value to set the chance of an additional drop per level of the Enchantment (in percentage).";
        cfgChance = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", chance, 1, 100);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
        chance = cfgChance.get();
    }
    // endregion
}
