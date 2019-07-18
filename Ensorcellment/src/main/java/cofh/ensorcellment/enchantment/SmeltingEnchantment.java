package cofh.ensorcellment.enchantment;

import cofh.lib.enchantment.EnchantmentCoFH;
import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.ensorcellment.init.ConfigEnsorc.COMMON_CONFIG;

public class SmeltingEnchantment extends EnchantmentCoFH {

    public SmeltingEnchantment(String id) {

        super(id, Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15;
    }

    public int getMaxEnchantability(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApply(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof ToolItem || supportsEnchantment(stack));
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {

        return super.canApplyTogether(ench) && ench != Enchantments.SILK_TOUCH;
    }

    // region IConfigSupport
    private ForgeConfigSpec.BooleanValue cfgEnable;

    @Override
    public void genConfig() {

        COMMON_CONFIG.push("Enchantment");
        COMMON_CONFIG.push("Smelting");

        String comment = "If TRUE, the Smelting Enchantment is available for various Tools.";
        cfgEnable = COMMON_CONFIG.comment(comment).define("Enable", true);

        COMMON_CONFIG.pop(2);
    }

    @Override
    public void refreshConfig() {

        enable = cfgEnable.get();
    }
    // endregion

    // region CONVERSION
    public static ItemStack getItemStack(World world, ItemStack stack) {

        IInventory inv = new Inventory(stack);

        IRecipe<?> recipe = world.getRecipeManager().getRecipe(IRecipeType.SMELTING, inv, world).orElse(null);

        if (recipe == null) {
            return ItemStack.EMPTY;
        }
        ItemStack result = recipe.getRecipeOutput();
        return result.isEmpty() ? ItemStack.EMPTY : ItemHelper.cloneStack(result, result.getCount() * stack.getCount());
    }
    // endregion
}
