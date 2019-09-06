package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;

public class AirWorkerEnchantment extends EnchantmentCoFH {

    public AirWorkerEnchantment(String id) {

        super(id, Rarity.RARE, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]{EquipmentSlotType.HEAD});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 1;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 40;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable && type != null && type.canEnchantItem(stack.getItem());
    }

    // region IDynamicConfig
    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Air Affinity");

        String comment = "If TRUE, the Air Affinity Enchantment is available for Helmets.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
    }
    // endregion
}
