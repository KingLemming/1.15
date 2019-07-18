package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.Constants.MAX_ENCHANT_LEVEL;

public class FrostWalkerEnchantmentImp extends EnchantmentCoFH {

    public FrostWalkerEnchantmentImp(String id) {

        super(id, Enchantment.Rarity.RARE, EnchantmentType.ARMOR_FEET, new EquipmentSlotType[]{EquipmentSlotType.FEET});
        maxLevel = 2;
    }

    @Override
    public int getMinEnchantability(int level) {

        return level * 10;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 15;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (type != null && type.canEnchantItem(item) || item instanceof HorseArmorItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.DEPTH_STRIDER;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;
    private ForgeConfigSpec.IntValue cfgLevel;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Override");
        COMMON_CONFIG.push("Frost Walker");

        String comment = "If TRUE, the Frost Walker Enchantment is replaced with a more configurable version which works on more items, such as Horse Armor.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        comment = "This option adjusts the maximum allowable level for the Enchantment.";
        cfgLevel = COMMON_CONFIG.comment(comment).defineInRange("Max Level", maxLevel, 1, MAX_ENCHANT_LEVEL);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        maxLevel = cfgLevel.get();
    }
    // endregion
}