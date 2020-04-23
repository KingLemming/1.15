package cofh.lib.item.override;

import cofh.lib.item.ItemCoFH;
import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ITEM_ACTIVE_DURATION;
import static cofh.lib.util.constants.Constants.TRUE;
import static cofh.lib.util.constants.NBTTags.TAG_ACTIVE;

public class HorseArmorItemCoFH extends HorseArmorItem {

    protected Supplier<Boolean> showEnchantEffect = TRUE;
    protected Supplier<Boolean> showInItemGroup = TRUE;
    protected boolean creative;
    protected int enchantability;

    public HorseArmorItemCoFH(int protection, String texture, Properties builder) {

        super(protection, texture, builder);
    }

    public HorseArmorItemCoFH(int protection, ResourceLocation texture, Properties builder) {

        super(protection, texture, builder);
    }

    public HorseArmorItemCoFH setCreative(boolean creative) {

        this.creative = creative;
        return this;
    }

    public HorseArmorItemCoFH setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    public final boolean isCreative() {

        return creative;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup.get()) {
            return;
        }
        super.fillItemGroup(group, items);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return showEnchantEffect.get() && stack.isEnchanted();
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {

        return SecurityHelper.hasSecurity(stack);
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
            stack.getTag().putLong(TAG_ACTIVE, living.world.getGameTime() + ITEM_ACTIVE_DURATION);
        }
    }

    public static void clearActive(ItemStack stack) {

        if (stack.hasTag()) {
            stack.getTag().remove(TAG_ACTIVE);
        }
    }

    public static boolean isCreative(ItemStack stack) {

        if (stack.getItem() instanceof ItemCoFH) {
            return ((ItemCoFH) stack.getItem()).isCreative();
        }
        return false;
    }
    // endregion
}
