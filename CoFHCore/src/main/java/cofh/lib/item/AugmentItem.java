package cofh.lib.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class AugmentItem extends ItemCoFH implements IAugmentItem {

    private final CompoundNBT augmentData;

    public AugmentItem(Properties builder, CompoundNBT augmentData) {

        super(builder);
        this.augmentData = augmentData;
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        super.addInformation(stack, worldIn, tooltip, flagIn);

        System.out.println(stack.getTag());
        System.out.println(augmentData);
    }

    // region IAugmentItem
    @Nullable
    @Override
    public CompoundNBT getAugmentData(ItemStack augment) {

        return augmentData;
    }
    // endregion
}
