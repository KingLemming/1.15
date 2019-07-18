package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;

public class InsightEnchantment extends EnchantmentCoFH {

    public static int experience = 4;

    public InsightEnchantment(String id) {

        super(id, Rarity.UNCOMMON, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 10 + (level - 1) * 9;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof ToolItem || item instanceof BowItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH;
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;
    private ForgeConfigSpec.IntValue cfgLevel;
    private ForgeConfigSpec.IntValue cfgExperience;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Insight");

        String comment = "If TRUE, the Insight Enchantment is available for various Tools and Weapons.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        comment = "Adjust this to change the base experience awarded per level of the Enchantment.";
        cfgExperience = COMMON_CONFIG.comment(comment).defineInRange("Effect Chance", experience, 1, 1000);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
        experience = cfgExperience.get();
    }
    // endregion
}
