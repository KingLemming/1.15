package cofh.thermal.expansion.plugins.jei.machine;

import cofh.lib.util.helpers.RenderHelper;
import cofh.thermal.core.plugins.jei.Drawables;
import cofh.thermal.core.plugins.jei.ThermalCategory;
import cofh.thermal.expansion.client.gui.machine.MachineRefineryScreen;
import cofh.thermal.expansion.util.recipes.machine.RefineryRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_REFINERY_BLOCK;

public class RefineryRecipeCategory extends ThermalCategory<RefineryRecipe> {

    protected IDrawableStatic tankInput;
    protected IDrawableStatic tankOutputA;
    protected IDrawableStatic tankOutputB;

    protected IDrawableStatic inputOverlay;
    protected IDrawableStatic outputOverlayA;
    protected IDrawableStatic outputOverlayB;

    public RefineryRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        super(guiHelper, uid);

        background = guiHelper.drawableBuilder(MachineRefineryScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_REFINERY_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        tankInput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_SMALL);
        tankOutputA = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOutputB = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        inputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_SMALL);
        outputOverlayA = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        outputOverlayB = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends RefineryRecipe> getRecipeClass() {

        return RefineryRecipe.class;
    }

    @Override
    public void setIngredients(RefineryRecipe recipe, IIngredients ingredients) {

        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
        ingredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, RefineryRecipe recipe, IIngredients ingredients) {

        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<FluidStack>> outputFluids = ingredients.getOutputs(VanillaTypes.FLUID);
        List<List<ItemStack>> outputItems = ingredients.getOutputs(VanillaTypes.ITEM);

        for (int i = 0; i < outputItems.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputItems.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, false, 96, 23);
        guiFluidStacks.init(0, true, 29, 6, 16, 32, TANK_SMALL, false, inputOverlay);
        guiFluidStacks.init(1, false, 126, 12, 16, 40, TANK_MEDIUM, false, outputOverlayA);
        guiFluidStacks.init(2, false, 144, 12, 16, 40, TANK_MEDIUM, false, outputOverlayB);

        if (!outputItems.isEmpty()) {
            guiItemStacks.set(0, outputItems.get(0));
        }
        guiFluidStacks.set(0, inputFluids.get(0));

        for (int i = 0; i < outputFluids.size(); ++i) {
            guiFluidStacks.set(i + 1, outputFluids.get(i));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 0);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(RefineryRecipe recipe, double mouseX, double mouseY) {

        super.draw(recipe, mouseX, mouseY);

        progressBackground.draw(57, 22);
        tankInput.draw(28, 5);
        tankOutputA.draw(125, 11);
        tankOutputB.draw(143, 11);
        speedBackground.draw(29, 40);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(57, 22, recipe.getInputFluids().get(0), 24, 16);
            progressFluidBackground.draw(57, 22);
            progressFluid.draw(57, 22);
        } else {
            progress.draw(57, 22);
        }
        speed.draw(29, 40);
    }

}
