package cofh.lib.item;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ITEM_ACTIVE_DURATION;
import static cofh.lib.util.constants.Constants.TRUE;
import static cofh.lib.util.constants.NBTTags.TAG_ACTIVE;

public class ItemCoFH extends Item {

    protected Supplier<Boolean> showEnchantEffect = TRUE;
    protected Supplier<Boolean> showInItemGroup = TRUE;
    protected boolean creative;
    protected int enchantability;

    public ItemCoFH(Properties builder) {

        super(builder);
    }

    public ItemCoFH setCreative(boolean creative) {

        this.creative = creative;
        return this;
    }

    public ItemCoFH setEnchantability(int enchantability) {

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

    @Nullable
    public Entity createEntity(World world, Entity location, ItemStack stack) {

        if (SecurityHelper.hasSecurity(stack)) {
            location.setInvulnerable(true);
            ((ItemEntity) location).lifespan = Integer.MAX_VALUE;
        }
        return null;
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
