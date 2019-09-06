package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.constants.Constants.MAX_ENCHANT_LEVEL;

public class VorpalEnchantment extends EnchantmentCoFH {

    public static int critBase = 5;
    public static int critLevel = 5;
    public static int critDamage = 10;
    public static int headBase = 5;
    public static int headLevel = 10;

    public VorpalEnchantment(String id) {

        super(id, Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 4;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    // region IDynamicConfig
    private ForgeConfigSpec.IntValue cfgCritBase;
    private ForgeConfigSpec.IntValue cfgCritLevel;
    private ForgeConfigSpec.IntValue cfgCritDamage;
    private ForgeConfigSpec.IntValue cfgHeadBase;
    private ForgeConfigSpec.IntValue cfgHeadLevel;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Vorpal");

        String comment = "If TRUE, the Vorpal Enchantment is available for various Weapons.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        comment = "Adjust this value to set the base critical hit chance of the Enchantment (in percentage).";
        cfgCritBase = COMMON_CONFIG.comment(comment).defineInRange("Base Critical Chance", critBase, 1, 100);

        comment = "Adjust this value to set the critical hit chance per level of the Enchantment (in percentage).";
        cfgCritLevel = COMMON_CONFIG.comment(comment).defineInRange("Critical Chance / Level", critLevel, 1, 100);

        comment = "Adjust this value to set the critical hit damage multiplier.";
        cfgCritDamage = COMMON_CONFIG.comment(comment).defineInRange("Critical Damage Multiplier", critDamage, 2, 1000);

        comment = "Adjust this value to set the base critical hit chance for the Enchantment (in percentage).";
        cfgHeadBase = COMMON_CONFIG.comment(comment).defineInRange("Base Head Drop Chance", headBase, 1, 100);

        comment = "Adjust this value to set the critical hit chance per level of the Enchantment (in percentage).";
        cfgHeadLevel = COMMON_CONFIG.comment(comment).defineInRange("Head Drop Chance / Level", headLevel, 1, 100);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
        critBase = cfgCritBase.get();
        critLevel = cfgCritLevel.get();
        critDamage = cfgCritDamage.get();
        headBase = cfgHeadBase.get();
        headLevel = cfgHeadLevel.get();
    }
    // endregion
}
