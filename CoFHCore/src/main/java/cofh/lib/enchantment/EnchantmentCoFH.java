package cofh.lib.enchantment;

import cofh.lib.util.IDynamicConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.lib.capability.CapabilityEnchantable.ENCHANTABLE_ITEM_CAPABILITY;

public abstract class EnchantmentCoFH extends Enchantment implements IDynamicConfig {

    protected boolean enable = true;
    protected boolean allowOnBooks = true;
    protected int maxLevel = 1;

    protected EnchantmentCoFH(String id, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {

        super(rarityIn, typeIn, slots);
        setRegistryName(id);
    }

    protected EnchantmentCoFH setEnable(boolean enable) {

        this.enable = enable;
        return this;
    }

    protected EnchantmentCoFH setAllowOnBooks(boolean allowOnBooks) {

        this.allowOnBooks = allowOnBooks;
        return this;
    }

    protected EnchantmentCoFH setMaxLevel(int maxLevel) {

        this.maxLevel = maxLevel;
        return this;
    }

    protected boolean supportsEnchantment(ItemStack stack) {

        return stack.getCapability(ENCHANTABLE_ITEM_CAPABILITY).filter(cap -> cap.supportsEnchantment(stack, this)).isPresent();
    }

    public boolean enabled() {

        return enable;
    }

    @Override
    public int getMaxLevel() {

        return maxLevel;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        return enable;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return canApply(stack);
    }

    @Override
    public boolean isAllowedOnBooks() {

        return enable && allowOnBooks;
    }

    // region IDynamicConfig
    protected ForgeConfigSpec.BooleanValue cfgEnable;
    protected ForgeConfigSpec.IntValue cfgLevel;
    protected ForgeConfigSpec.IntValue cfgChance;

    public void genConfig() {

    }

    public void refreshConfig() {

    }
    // endregion
}
