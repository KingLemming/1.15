package cofh.lib.item.override;

import cofh.lib.util.helpers.SecurityHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.helpers.StringHelper.canLocalize;
import static cofh.lib.util.helpers.StringHelper.getInfoTextComponent;

public class ArrowItemCoFH extends ArrowItem {

    protected boolean showEnchantEffect = true;
    protected boolean showInItemGroup = true;

    public ArrowItemCoFH(Properties builder) {

        super(builder);
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

        if (!showInItemGroup) {
            return;
        }
        super.fillItemGroup(group, items);
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

}
