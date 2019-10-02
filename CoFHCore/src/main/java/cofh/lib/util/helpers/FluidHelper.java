package cofh.lib.util.helpers;

import com.google.common.collect.Lists;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cofh.lib.util.constants.Tags.TAG_POTION;

public class FluidHelper {

    public static HashMap<Fluid, Integer> colorCache = new HashMap<>();

    public static int getFluidColor(FluidStack stack) {

        int color = -1;
        if (stack != null && stack.getFluid() != null) {
            color = colorCache.getOrDefault(stack.getFluid().getAttributes().getStill(stack), stack.getFluid().getAttributes().getColor(stack) != 0xffffffff ? stack.getFluid().getAttributes().getColor(stack) : ColorHelper.getColorFrom(stack.getFluid().getAttributes().getStill(stack)));
            if (!colorCache.containsKey(stack.getFluid())) colorCache.put(stack.getFluid(), color);
        }
        return color;
    }

    public static int getFluidColor(Fluid fluid) {

        int color = -1;
        if (fluid != null) {
            color = colorCache.getOrDefault(fluid, fluid.getAttributes().getColor() != 0xffffffff ? fluid.getAttributes().getColor() : ColorHelper.getColorFrom(fluid.getAttributes().getStillTexture()));
            if (!colorCache.containsKey(fluid)) colorCache.put(fluid, color);
        }
        return color;
    }

    // region POTION HELPERS
    public static boolean hasPotionTag(FluidStack stack) {

        return stack != null && stack.getTag() != null && stack.getTag().contains(TAG_POTION);
    }

    public static void addPotionTooltip(FluidStack stack, List<ITextComponent> list) {

        addPotionTooltip(stack, list, 1.0F);
    }

    public static void addPotionTooltip(FluidStack stack, List<ITextComponent> lores, float durationFactor) {

        if (stack.isEmpty()) {
            return;
        }
        List<EffectInstance> list = PotionUtils.getEffectsFromTag(stack.getTag());
        List<Tuple<String, AttributeModifier>> list1 = Lists.newArrayList();

        if (list.isEmpty()) {
            lores.add((new TranslationTextComponent("effect.none")).applyTextStyle(TextFormatting.GRAY));
        } else {
            for (EffectInstance effectinstance : list) {
                ITextComponent itextcomponent = new TranslationTextComponent(effectinstance.getEffectName());
                Effect effect = effectinstance.getPotion();
                Map<IAttribute, AttributeModifier> map = effect.getAttributeModifierMap();
                if (!map.isEmpty()) {
                    for (Map.Entry<IAttribute, AttributeModifier> entry : map.entrySet()) {
                        AttributeModifier attributemodifier = entry.getValue();
                        AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.getName(), effect.getAttributeModifierAmount(effectinstance.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                        list1.add(new Tuple<>(entry.getKey().getName(), attributemodifier1));
                    }
                }
                if (effectinstance.getAmplifier() > 0) {
                    itextcomponent.appendText(" ").appendSibling(new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
                }
                if (effectinstance.getDuration() > 20) {
                    itextcomponent.appendText(" (").appendText(EffectUtils.getPotionDurationString(effectinstance, durationFactor)).appendText(")");
                }
                lores.add(itextcomponent.applyTextStyle(effect.getEffectType().getColor()));
            }
        }
        if (!list1.isEmpty()) {
            lores.add(new StringTextComponent(""));
            lores.add((new TranslationTextComponent("potion.whenDrank")).applyTextStyle(TextFormatting.DARK_PURPLE));

            for (Tuple<String, AttributeModifier> tuple : list1) {
                AttributeModifier attributemodifier2 = tuple.getB();
                double d0 = attributemodifier2.getAmount();
                double d1;
                if (attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier2.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    d1 = attributemodifier2.getAmount();
                } else {
                    d1 = attributemodifier2.getAmount() * 100.0D;
                }
                if (d0 > 0.0D) {
                    lores.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + (String) tuple.getA()))).applyTextStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    lores.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + (String) tuple.getA()))).applyTextStyle(TextFormatting.RED));
                }
            }
        }
    }

    // region PROPERTY HELPERS
    public static int luminosity(Fluid fluid) {

        return fluid == null ? 0 : fluid.getAttributes().getLuminosity();
    }

    public static int luminosity(FluidStack stack) {

        return stack == null ? 0 : luminosity(stack.getFluid());
    }

    public static int density(Fluid fluid) {

        return fluid == null ? 0 : fluid.getAttributes().getDensity();
    }

    public static int density(FluidStack stack) {

        return stack == null ? 0 : density(stack.getFluid());
    }

    public static int temperature(Fluid fluid) {

        return fluid == null ? 0 : fluid.getAttributes().getTemperature();
    }

    public static int temperature(FluidStack stack) {

        return stack == null ? 0 : temperature(stack.getFluid());
    }

    public static int viscosity(Fluid fluid) {

        return fluid == null ? 0 : fluid.getAttributes().getViscosity();
    }

    public static int viscosity(FluidStack stack) {

        return stack == null ? 0 : viscosity(stack.getFluid());
    }
    // endregion

}