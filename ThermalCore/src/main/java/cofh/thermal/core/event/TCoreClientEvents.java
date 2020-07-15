package cofh.thermal.core.event;

import cofh.lib.util.helpers.AugmentDataHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.constants.NBTTags.*;
import static cofh.lib.util.helpers.StringHelper.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ID_THERMAL)
public class TCoreClientEvents {

    private TCoreClientEvents() {

    }

    private static final Set<String> ATTR_ADD = new HashSet<>();
    private static final Set<String> ATTR_MAX = new HashSet<>();
    private static final Set<String> ATTR_MULT = new HashSet<>();
    private static final Set<String> ATTR_INV = new HashSet<>();
    private static final Set<String> ATTR_INT = new HashSet<>();

    // Sets storing if an attribute is multiplicative or additive, and also if the calculation is maximized, or higher values are bad.
    static {
        // Additive
        ATTR_ADD.addAll(Arrays.asList(
                TAG_AUGMENT_AREA_RADIUS,

                TAG_AUGMENT_DYNAMO_PRODUCTION,

                TAG_AUGMENT_MACHINE_POWER,
                TAG_AUGMENT_MACHINE_PRIMARY,
                TAG_AUGMENT_MACHINE_SECONDARY,

                TAG_AUGMENT_POTION_AMPLIFIER,
                TAG_AUGMENT_POTION_DURATION
        ));
        // Multiplicative
        ATTR_MULT.addAll(Arrays.asList(
                TAG_AUGMENT_BASE_MOD,

                TAG_AUGMENT_ENERGY_STORAGE,
                TAG_AUGMENT_ENERGY_XFER,

                TAG_AUGMENT_FLUID_STORAGE,

                TAG_AUGMENT_DYNAMO_EFFICIENCY,

                TAG_AUGMENT_MACHINE_CATALYST,
                TAG_AUGMENT_MACHINE_ENERGY,
                TAG_AUGMENT_MACHINE_XP
        ));
        // Maximized (Not exclusive with other sets)
        ATTR_MAX.addAll(Arrays.asList(
                TAG_AUGMENT_BASE_MOD,

                TAG_AUGMENT_ENERGY_STORAGE,
                TAG_AUGMENT_ENERGY_XFER,

                TAG_AUGMENT_FLUID_STORAGE,

                TAG_AUGMENT_MACHINE_MIN_OUTPUT
        ));
        // Inverse - HIGHER = WORSE (Not exclusive with other sets)
        ATTR_INV.addAll(Arrays.asList(
                TAG_AUGMENT_MACHINE_CATALYST,
                TAG_AUGMENT_MACHINE_ENERGY
        ));
        // Integer - Mod is NOT a % (Not exclusive with other sets)
        ATTR_INT.addAll(Arrays.asList(
                TAG_AUGMENT_AREA_RADIUS,

                TAG_AUGMENT_POTION_AMPLIFIER
        ));
        // Modifiers are squared
    }

    private static boolean isAdditive(String mod) {

        return ATTR_ADD.contains(mod);
    }

    private static boolean isMultiplicative(String mod) {

        return ATTR_MULT.contains(mod);
    }

    private static boolean isMaximized(String mod) {

        return ATTR_MAX.contains(mod);
    }

    private static boolean isInverse(String mod) {

        return ATTR_INV.contains(mod);
    }

    private static boolean isInteger(String mod) {

        return ATTR_INT.contains(mod);
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
            if (augmentData == null || augmentData.isEmpty()) {
                return;
            }
            String type = augmentData.getString(TAG_TYPE);
            if (!type.isEmpty()) {
                tooltip.add(getTextComponent("info.cofh.type")
                        .applyTextStyle(TextFormatting.YELLOW)
                        .appendText(": ")
                        .appendSibling(getTextComponent("info.thermal.augment.type." + type).applyTextStyle(TextFormatting.WHITE)));
            }
            for (String mod : augmentData.keySet()) {
                if (mod.equals(TAG_TYPE) || !canLocalize("info.thermal.augment.attr." + mod)) {
                    continue;
                }
                float value = augmentData.getFloat(mod);
                boolean bad = isAdditive(mod) && value < 0
                        || isAdditive(mod) && value > 0 && isInverse(mod)
                        || isMultiplicative(mod) && (isInverse(mod) ? value > 1.0 : value < 1.0);

                ITextComponent modText = new StringTextComponent("" +
                        (isAdditive(mod) && value > 0 ? "+" : "") +
                        (isInteger(mod) ? DF0.format(value) : isMultiplicative(mod) ? DF2.format(value) + "x" : DF0.format(value * 100) + "%")
                ).applyTextStyle(bad ? TextFormatting.RED : TextFormatting.GREEN);

                tooltip.add(getTextComponent("info.thermal.augment.attr." + mod)
                        .appendText(": ")
                        .applyTextStyle(TextFormatting.GRAY)
                        .appendSibling(modText));
            }
        }
    }

}
