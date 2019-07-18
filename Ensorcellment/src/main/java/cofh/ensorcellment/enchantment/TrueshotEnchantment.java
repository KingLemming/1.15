package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.helpers.ArcheryHelper.validBow;

public class TrueshotEnchantment extends EnchantmentCoFH {

    public TrueshotEnchantment(String id) {

        super(id, Rarity.UNCOMMON, EnchantmentType.BOW, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1 + (level - 1) * 10;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable && (validBow(stack) || supportsEnchantment(stack));
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Trueshot");

        String comment = "If TRUE, the Trueshot Enchantment is available for various Bows.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
    }
    // endregion
}
