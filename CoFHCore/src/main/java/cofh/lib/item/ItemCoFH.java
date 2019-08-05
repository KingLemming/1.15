package cofh.lib.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.Constants.ITEM_ACTIVE_DURATION;
import static cofh.lib.util.Constants.TAG_ACTIVE;
import static cofh.lib.util.helpers.StringHelper.canLocalize;
import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;

public class ItemCoFH extends Item {

    protected String info;
    protected boolean showInItemGroup = true;
    protected boolean creative;

    public ItemCoFH(Properties properties) {

        super(properties);
    }

    public ItemCoFH setCreative(boolean creative) {

        this.creative = creative;
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
    public boolean hasCustomEntity(ItemStack stack) {

        // TODO: Fix
        return false;
        // return SecurityHelper.hasSecurity(stack);
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

    //    // region IModelRegister
    //    @SideOnly (Side.CLIENT)
    //    public void registerModel() {
    //
    //        if (group.isEmpty()) {
    //            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    //        } else {
    //            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().getResourceDomain() + ":" + group + "/" + getRegistryName().getResourcePath(), "inventory"));
    //        }
    //    }
    //
    //    @Override
    //    public void generateModelFiles() {
    //
    //        String group = getGroup().isEmpty() ? "" : getGroup() + "/";
    //        String model = "{\"parent\":\"item/generated\",\"textures\":{\"layer0\":\"" + getRegistryName().getResourceDomain() + ":items/" + group + getRegistryName().getResourcePath() + "\"}}";
    //
    //        try {
    //            File itemModel = new File(configDir, "/dev/" + getRegistryName().getResourceDomain() + "/models/item/" + group + getRegistryName().getResourcePath() + ".json");
    //            FileUtils.writeStringToFile(itemModel, Utils.createPrettyJSON(model), Charset.forName("UTF-8"));
    //        } catch (Throwable t) {
    //            // pokemon!
    //        }
    //    }
    //    // endregion
}
