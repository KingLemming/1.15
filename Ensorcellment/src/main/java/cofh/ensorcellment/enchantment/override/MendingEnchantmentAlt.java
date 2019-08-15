package cofh.ensorcellment.enchantment.override;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;
import static cofh.lib.util.constants.Constants.ID_ENSORCELLMENT;

public class MendingEnchantmentAlt extends EnchantmentCoFH {

    public static float anvilDamage = 0.03F;

    public MendingEnchantmentAlt(String id) {

        super(id, Enchantment.Rarity.RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
        name = "enchantment." + ID_ENSORCELLMENT + ".preservation";
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {

        return enchantmentLevel * 25;
    }

    public int getMaxEnchantability(int enchantmentLevel) {

        return getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public boolean isTreasureEnchantment() {

        return true;
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;
    private ForgeConfigSpec.IntValue cfgDamage;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Override");
        COMMON_CONFIG.push("Mending");

        String comment = "If TRUE, the Mending Enchantment is replaced with a new Enchantment - Preservation. This enchantment allows you to repair items at an Anvil without paying an increasing XP cost for every time you repair it. Additionally, these repairs have a much lower chance of damaging the anvil.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", false);

        comment = "Adjust this value to set the chance of an Anvil being damaged when used to repair an item with Preservation (in percentage).";
        cfgDamage = COMMON_CONFIG.comment(comment).defineInRange("Anvil Damage Chance", (int) (anvilDamage * 100), 0, 12);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
        name = "enchantment." + (enable ? ID_ENSORCELLMENT + ".preservation" : "minecraft.mending");

        anvilDamage = cfgDamage.get() / 100.0F;
    }
    // endregion

}
