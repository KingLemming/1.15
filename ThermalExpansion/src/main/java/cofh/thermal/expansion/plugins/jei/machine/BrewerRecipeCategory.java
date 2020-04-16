package cofh.thermal.expansion.plugins.jei.machine;

import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.plugins.jei.Drawables;
import cofh.thermal.core.plugins.jei.ThermalCategory;
import cofh.thermal.core.util.recipes.machine.BrewerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineBrewerScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_BREWER_BLOCK;

public class BrewerRecipeCategory extends ThermalCategory<BrewerRecipe> {

    protected IDrawableStatic tankInput;
    protected IDrawableStatic tankOutput;

    protected IDrawableStatic inputOverlay;
    protected IDrawableStatic outputOverlay;

    public BrewerRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        super(guiHelper, uid);

        background = guiHelper.drawableBuilder(MachineBrewerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        localizedName = StringHelper.localize(MACHINE_BREWER_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_ALCHEMY);

        tankInput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOutput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        inputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        outputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_ALCHEMY), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends BrewerRecipe> getRecipeClass() {

        return BrewerRecipe.class;
    }

    @Override
    public void setIngredients(BrewerRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, BrewerRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputItems = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<FluidStack>> outputs = ingredients.getOutputs(VanillaTypes.FLUID);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 51, 14);
        guiFluidStacks.init(0, true, 25, 11, 16, 40, TANK_MEDIUM, false, inputOverlay);
        guiFluidStacks.init(1, false, 116, 11, 16, 40, TANK_MEDIUM, false, outputOverlay);

        guiItemStacks.set(0, inputItems.get(0));
        guiFluidStacks.set(0, inputFluids.get(0));
        guiFluidStacks.set(1, outputs.get(0));

        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(BrewerRecipe recipe, double mouseX, double mouseY) {

        super.draw(recipe, mouseX, mouseY);

        progressBackground.draw(78, 23);
        tankInput.draw(24, 10);
        tankOutput.draw(115, 10);
        speedBackground.draw(52, 34);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(78, 23, recipe.getInputFluids().get(0), 24, 16);
            progressFluidBackground.draw(78, 23);
            progressFluid.draw(78, 23);
        } else {
            progress.draw(78, 23);
        }
        speed.draw(52, 34);
    }

}
