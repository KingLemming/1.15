package cofh.lib.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.BooleanSupplier;

import static cofh.lib.util.constants.Constants.TRUE;

public class ItemCoFH extends Item implements ICoFHItem {

    protected BooleanSupplier showInGroups = SHOW;
    protected BooleanSupplier showEnchantEffect = TRUE;

    protected int enchantability;

    public ItemCoFH(Properties builder) {

        super(builder);
    }

    public ItemCoFH setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    public ItemCoFH setShowInGroups(BooleanSupplier showInGroups) {

        this.showInGroups = showInGroups;
        return this;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInGroups.getAsBoolean()) {
            return;
        }
        super.fillItemGroup(group, items);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return showEnchantEffect.getAsBoolean() && stack.isEnchanted();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {

        return enchantability > 0;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {

        return enchantability;
    }

    //    @Override
    //    public String getHighlightTip(ItemStack stack, String displayName) {
    //
    //        if (isActive(stack)) {
    //            return "";
    //        }
    //        return displayName;
    //    }
    //
    //    // region HELPERS
    //    public static boolean isActive(ItemStack stack) {
    //
    //        return stack.hasTag() && stack.getTag().getBoolean(TAG_ACTIVE);
    //    }
    //
    //    public static void clearActive(ItemStack stack) {
    //
    //        if (stack.hasTag()) {
    //            stack.getTag().remove(TAG_ACTIVE);
    //        }
    //    }
    //    // endregion
}
