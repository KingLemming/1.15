package cofh.thermal.expansion.plugins.jei.machine;

import cofh.lib.util.helpers.RenderHelper;
import cofh.thermal.core.plugins.jei.Drawables;
import cofh.thermal.core.plugins.jei.ThermalRecipeCategory;
import cofh.thermal.expansion.client.gui.machine.MachineInsolatorScreen;
import cofh.thermal.expansion.util.recipes.machine.InsolatorRecipe;
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
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.plugins.jei.ThermalJeiPlugin.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_BLOCK;

public class InsolatorRecipeCategory extends ThermalRecipeCategory<InsolatorRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public InsolatorRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        super(guiHelper, uid);

        background = guiHelper.drawableBuilder(MachineInsolatorScreen.TEXTURE, 26, 11, 130, 62)
                .addPadding(0, 0, 16, 18)
                .build();
        name = getTextComponent(MACHINE_INSOLATOR_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SUN);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SUN), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends InsolatorRecipe> getRecipeClass() {

        return InsolatorRecipe.class;
    }

    @Override
    public void setIngredients(InsolatorRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, InsolatorRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputs.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 51, 5);
        guiItemStacks.init(1, false, 105, 14);
        guiItemStacks.init(2, false, 123, 14);
        guiItemStacks.init(3, false, 105, 32);
        guiItemStacks.init(4, false, 123, 32);

        guiFluidStacks.init(0, false, 25, 11, 16, 40, tankSize(TANK_MEDIUM), false, tankOverlay(tankOverlay));

        guiItemStacks.set(0, inputs.get(0));
        guiFluidStacks.set(0, inputFluids.get(0));

        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 1, outputs.get(i));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(InsolatorRecipe recipe, double mouseX, double mouseY) {

        super.draw(recipe, mouseX, mouseY);

        progressBackground.draw(76, 23);
        tankBackground.draw(24, 10);
        speedBackground.draw(52, 24);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(76, 23, recipe.getInputFluids().get(0), 24, 16);
            progressFluidBackground.draw(76, 23);
            progressFluid.draw(76, 23);
        } else {
            progress.draw(76, 23);
        }
        speed.draw(52, 24);
    }

}
