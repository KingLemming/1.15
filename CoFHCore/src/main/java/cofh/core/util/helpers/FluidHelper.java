package cofh.core.util.helpers;

import cofh.core.fluid.FluidStorageCoFH;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.EmptyFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static cofh.core.util.constants.NBTTags.TAG_POTION;
import static net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE;

public class FluidHelper {

    private FluidHelper() {

    }

    public static final Predicate<FluidStack> IS_WATER = e -> e.getFluid().equals(Fluids.WATER);
    public static final Predicate<FluidStack> IS_LAVA = e -> e.getFluid().equals(Fluids.LAVA);

    public static HashMap<Fluid, Integer> colorCache = new HashMap<>();

    public static int getFluidColor(FluidStack stack) {

        int color = -1;
        if (stack != null && stack.getFluid() != null) {
            color = colorCache.getOrDefault(stack.getFluid(), stack.getFluid().getAttributes().getColor(stack) != 0xffffffff ? stack.getFluid().getAttributes().getColor(stack) : ColorHelper.getColorFrom(stack.getFluid().getAttributes().getStillTexture(stack)));
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

    public static int fluidHashcodeNoTag(FluidStack stack) {

        return stack.getFluid().hashCode();
    }

    public static int fluidHashcode(FluidStack stack) {

        return stack.getTag() != null ? stack.getFluid().hashCode() + 31 * stack.getTag().hashCode() : stack.getFluid().hashCode();
    }

    // region COMPARISON
    public static boolean fluidsEqual(FluidStack resourceA, FluidStack resourceB) {

        return resourceA != null && resourceA.isFluidEqual(resourceB) || resourceA == null && resourceB == null;
    }

    public static boolean fluidsEqual(Fluid fluidA, FluidStack resourceB) {

        return fluidA != null && resourceB != null && fluidA == resourceB.getFluid();
    }

    public static boolean fluidsEqual(FluidStack resourceA, Fluid fluidB) {

        return fluidB != null && resourceA != null && fluidB == resourceA.getFluid();
    }

    public static boolean fluidsEqual(Fluid fluidA, Fluid fluidB) {

        return fluidA != null && fluidA.equals(fluidB);
    }
    // endregion

    // region BLOCK HELPERS
    public static boolean isWater(BlockState state) {

        return state.getBlock() == Blocks.WATER;
    }
    // endregion

    // region BLOCK TRANSFER
    public static boolean extractFromAdjacent(TileEntity tile, FluidStorageCoFH tank, int amount, Direction side) {

        amount = Math.min(amount, tank.getSpace());
        if (!tank.getFluidStack().isEmpty()) {
            return extractFromAdjacent(tile, tank, new FluidStack(tank.getFluidStack(), amount), side);
        }
        TileEntity adjTile = BlockHelper.getAdjacentTileEntity(tile, side);
        Direction opposite = side.getOpposite();

        IFluidHandler handler = getFluidHandlerCap(adjTile, opposite);
        if (handler == EmptyFluidHandler.INSTANCE) {
            return false;
        }
        FluidStack drainStack = handler.drain(amount, IFluidHandler.FluidAction.SIMULATE);
        int drainAmount = tank.fill(drainStack, EXECUTE);
        if (drainAmount > 0) {
            handler.drain(drainAmount, EXECUTE);
            return true;
        }
        return false;
    }

    public static boolean extractFromAdjacent(TileEntity tile, FluidStorageCoFH tank, FluidStack resource, Direction side) {

        TileEntity adjTile = BlockHelper.getAdjacentTileEntity(tile, side);
        Direction opposite = side.getOpposite();

        IFluidHandler handler = getFluidHandlerCap(adjTile, opposite);
        if (handler == EmptyFluidHandler.INSTANCE) {
            return false;
        }
        FluidStack drainStack = handler.drain(resource, IFluidHandler.FluidAction.SIMULATE);
        int drainAmount = tank.fill(drainStack, EXECUTE);
        if (drainAmount > 0) {
            handler.drain(new FluidStack(resource, drainAmount), EXECUTE);
            return true;
        }
        return false;
    }

    public static boolean insertIntoAdjacent(TileEntity tile, FluidStorageCoFH tank, int amount, Direction side) {

        if (tank.isEmpty()) {
            return false;
        }
        amount = Math.min(amount, tank.getAmount());

        TileEntity adjTile = BlockHelper.getAdjacentTileEntity(tile, side);
        Direction opposite = side.getOpposite();

        IFluidHandler handler = getFluidHandlerCap(adjTile, opposite);
        if (handler == EmptyFluidHandler.INSTANCE) {
            return false;
        }
        int fillAmount = handler.fill(new FluidStack(tank.getFluidStack(), amount), EXECUTE);
        if (fillAmount > 0) {
            tank.drain(fillAmount, EXECUTE);
            return true;
        }
        return false;
    }

    public static boolean hasFluidHandlerCap(TileEntity tile, Direction face) {

        return tile != null && tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).isPresent();
    }

    public static IFluidHandler getFluidHandlerCap(TileEntity tile, Direction face) {

        return tile == null ? EmptyFluidHandler.INSTANCE : tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, face).orElse(EmptyFluidHandler.INSTANCE);
    }
    // endregion

    // region CAPABILITY HELPERS

    public static boolean hasFluidHandlerCap(ItemStack item) {

        return !item.isEmpty() && item.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent();
    }

    /**
     * Attempts to drain the item to an IFluidHandler.
     *
     * @param stack   The stack to drain from.
     * @param handler The IFluidHandler to fill.
     * @param player  The player using the item.
     * @param hand    The hand the player is holding the item in.
     * @return If the interaction was successful.
     */
    public static boolean drainItemToHandler(ItemStack stack, IFluidHandler handler, PlayerEntity player, Hand hand) {

        if (stack.isEmpty() || handler == null || player == null) {
            return false;
        }
        IItemHandler playerInv = new InvWrapper(player.inventory);
        FluidActionResult result = FluidUtil.tryEmptyContainerAndStow(stack, handler, playerInv, Integer.MAX_VALUE, player, true);
        if (result.isSuccess()) {
            player.setHeldItem(hand, result.getResult());
            return true;
        }
        return false;
    }

    /**
     * Attempts to fill the item from an IFluidHandler.
     *
     * @param stack   The stack to fill.
     * @param handler The IFluidHandler to drain from.
     * @param player  The player using the item.
     * @param hand    The hand the player is holding the item in.
     * @return If the interaction was successful.
     */
    public static boolean fillItemFromHandler(ItemStack stack, IFluidHandler handler, PlayerEntity player, Hand hand) {

        if (stack.isEmpty() || handler == null || player == null) {
            return false;
        }
        IItemHandler playerInv = new InvWrapper(player.inventory);
        FluidActionResult result = FluidUtil.tryFillContainerAndStow(stack, handler, playerInv, Integer.MAX_VALUE, player, true);
        if (result.isSuccess()) {
            player.setHeldItem(hand, result.getResult());
            return true;
        }
        return false;
    }

    /**
     * Attempts to interact the item with an IFluidHandler.
     * Interaction will always try and fill the item first, if this fails it will attempt to drain the item.
     *
     * @param stack   The stack to interact with.
     * @param handler The Handler to fill / drain.
     * @param player  The player using the item.
     * @param hand    The hand the player is holding the item in.
     * @return If any interaction with the handler was successful.
     */
    public static boolean interactWithHandler(ItemStack stack, IFluidHandler handler, PlayerEntity player, Hand hand) {

        return fillItemFromHandler(stack, handler, player, hand) || drainItemToHandler(stack, handler, player, hand);
    }

    // endregion

    // region POTION HELPERS
    public static boolean hasPotionTag(FluidStack stack) {

        return !stack.isEmpty() && stack.getTag() != null && stack.getTag().contains(TAG_POTION);
    }

    public static void addPotionTooltipStrings(FluidStack stack, List<String> list) {

        ArrayList<ITextComponent> lores = new ArrayList<>();
        addPotionTooltip(stack, lores, 1.0F);
        for (ITextComponent text : lores) {
            list.add(text.getFormattedText());
        }
    }

    public static void addPotionTooltip(FluidStack stack, List<ITextComponent> list) {

        addPotionTooltip(stack, list, 1.0F);
    }

    public static void addPotionTooltip(FluidStack stack, List<ITextComponent> lores, float durationFactor) {

        if (stack.isEmpty()) {
            return;
        }
        addPotionTooltip(PotionUtils.getEffectsFromTag(stack.getTag()), lores, durationFactor);
    }

    public static void addPotionTooltip(List<EffectInstance> effects, List<ITextComponent> lores, float durationFactor) {

        List<Tuple<String, AttributeModifier>> list1 = Lists.newArrayList();

        if (effects.isEmpty()) {
            lores.add((new TranslationTextComponent("effect.none")).applyTextStyle(TextFormatting.GRAY));
        } else {
            for (EffectInstance effectinstance : effects) {
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
                    lores.add((new TranslationTextComponent("attribute.modifier.plus." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + tuple.getA()))).applyTextStyle(TextFormatting.BLUE));
                } else if (d0 < 0.0D) {
                    d1 = d1 * -1.0D;
                    lores.add((new TranslationTextComponent("attribute.modifier.take." + attributemodifier2.getOperation().getId(), ItemStack.DECIMALFORMAT.format(d1), new TranslationTextComponent("attribute.name." + tuple.getA()))).applyTextStyle(TextFormatting.RED));
                }
            }
        }
    }
    // endregion

    // region PROPERTY HELPERS
    public static int luminosity(FluidStack stack) {

        return !stack.isEmpty() && stack.getFluid() != null ? stack.getFluid().getAttributes().getLuminosity(stack) : 0;
    }

    public static int density(FluidStack stack) {

        return !stack.isEmpty() && stack.getFluid() != null ? stack.getFluid().getAttributes().getDensity(stack) : 0;
    }

    public static boolean gaseous(FluidStack stack) {

        return !stack.isEmpty() && stack.getFluid() != null && stack.getFluid().getAttributes().isGaseous();
    }

    public static int temperature(FluidStack stack) {

        return !stack.isEmpty() && stack.getFluid() != null ? stack.getFluid().getAttributes().getTemperature(stack) : 0;
    }

    public static int viscosity(FluidStack stack) {

        return !stack.isEmpty() && stack.getFluid() != null ? stack.getFluid().getAttributes().getViscosity(stack) : 0;
    }
    // endregion

}