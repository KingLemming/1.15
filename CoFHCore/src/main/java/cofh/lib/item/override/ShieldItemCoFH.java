package cofh.lib.item.override;

import cofh.lib.item.ICoFHItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.BooleanSupplier;

import static cofh.lib.util.constants.Constants.ITEM_TIMER_DURATION;
import static cofh.lib.util.constants.Constants.TRUE;
import static cofh.lib.util.constants.NBTTags.TAG_ACTIVE;

public class ShieldItemCoFH extends ShieldItem implements ICoFHItem {

    protected BooleanSupplier showInGroups = TRUE;
    protected BooleanSupplier showEnchantEffect = TRUE;

    protected int enchantability;

    public ShieldItemCoFH(Properties builder) {

        super(builder);
    }

    public ShieldItemCoFH setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    public ShieldItemCoFH setShowInGroups(BooleanSupplier showInGroups) {

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
    public int getItemEnchantability() {

        return enchantability;
    }

    @Override
    public String getHighlightTip(ItemStack stack, String displayName) {

        if (isActive(stack)) {
            return "";
        }
        return displayName;
    }

    // region HELPERS
    public static boolean isActive(ItemStack stack) {

        return stack.hasTag() && stack.getTag().contains(TAG_ACTIVE);
    }

    public static void setActive(ItemStack stack, LivingEntity living) {

        if (stack.hasTag()) {
            stack.getTag().putLong(TAG_ACTIVE, living.world.getGameTime() + ITEM_TIMER_DURATION);
        }
    }

    public static void clearActive(ItemStack stack) {

        if (stack.hasTag()) {
            stack.getTag().remove(TAG_ACTIVE);
        }
    }
    // endregion
}
