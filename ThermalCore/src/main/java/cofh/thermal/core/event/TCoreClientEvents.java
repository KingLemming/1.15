package cofh.thermal.core.event;

import cofh.lib.util.helpers.AugmentDataHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_THERMAL)
public class TCoreClientEvents {

    private TCoreClientEvents() {

    }

    @SubscribeEvent
    public static void handleItemTooltipEvent(ItemTooltipEvent event) {

        List<ITextComponent> tooltip = event.getToolTip();
        if (tooltip.isEmpty()) {
            return;
        }
        ItemStack stack = event.getItemStack();

        if (AugmentDataHelper.hasAugmentData(stack)) {
            CompoundNBT augmentData = AugmentDataHelper.getAugmentData(stack);
            if (augmentData == null) {
                // This should never happen.
                return;
            }
            tooltip.add(new StringTextComponent(augmentData.toString()));
        }
    }

}
