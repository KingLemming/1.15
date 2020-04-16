package cofh.thermal.expansion.plugins.jei.machine;

import cofh.lib.util.helpers.RenderHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.plugins.jei.Drawables;
import cofh.thermal.core.plugins.jei.ThermalCategory;
import cofh.thermal.core.util.recipes.machine.ChillerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineChillerScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CHILLER_BLOCK;

public class ChillerRecipeCategory extends ThermalCategory<ChillerRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public ChillerRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        super(guiHelper, uid);

        background = guiHelper.drawableBuilder(MachineChillerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        localizedName = StringHelper.localize(MACHINE_CHILLER_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SNOWFLAKE);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SNOWFLAKE), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends ChillerRecipe> getRecipeClass() {

        return ChillerRecipe.class;
    }

    @Override
    public void setIngredients(ChillerRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, ChillerRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputItems = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<ItemStack>> outputItems = ingredients.getOutputs(VanillaTypes.ITEM);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 51, 14);
        guiItemStacks.init(1, false, 114, 23);
        guiFluidStacks.init(0, true, 25, 11, 16, 40, TANK_MEDIUM, false, tankOverlay);

        if (!inputItems.isEmpty()) {
            guiItemStacks.set(0, inputItems.get(0));
        }
        guiItemStacks.set(1, outputItems.get(0));

        if (!inputFluids.isEmpty()) {
            guiFluidStacks.set(0, inputFluids.get(0));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(ChillerRecipe recipe, double mouseX, double mouseY) {

        super.draw(recipe, mouseX, mouseY);

        progressBackground.draw(78, 23);
        tankBackground.draw(24, 10);
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
