package cofh.lib.item.override;

import cofh.lib.item.ItemCoFH;
import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.constants.Constants.ITEM_ACTIVE_DURATION;
import static cofh.lib.util.constants.Tags.TAG_ACTIVE;
import static cofh.lib.util.helpers.StringHelper.canLocalize;
import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;

public class ShieldItemCoFH extends ShieldItem {

    protected String info;
    protected boolean showEnchantEffect = true;
    protected boolean showInItemGroup = true;
    protected boolean creative;
    protected int enchantability;

    public ShieldItemCoFH(Properties properties) {

        super(properties);
    }

    public ShieldItemCoFH setCreative(boolean creative) {

        this.creative = creative;
        return this;
    }

    public ShieldItemCoFH setEnchantability(int enchantability) {

        this.enchantability = enchantability;
        return this;
    }

    public final boolean isCreative() {

        return creative;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup) {
            return;
        }
        super.fillItemGroup(group, items);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (info == null && this.getRegistryName() != null) {
            info = "info." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath();
        }
        if (canLocalize(info)) {
            tooltip.add(getInfoTextComponent(info));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return showEnchantEffect && stack.isEnchanted();
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
