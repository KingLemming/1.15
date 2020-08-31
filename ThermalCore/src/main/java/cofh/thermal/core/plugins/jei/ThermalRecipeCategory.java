package cofh.thermal.core.plugins.jei;

import cofh.core.util.helpers.StringHelper;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.constants.Constants.BASE_CHANCE;
import static cofh.core.util.helpers.StringHelper.localize;

public abstract class ThermalRecipeCategory<T extends ThermalRecipe> implements IRecipeCategory<T> {

    protected final int ENERGY_X = 2;
    protected final int ENERGY_Y = 10;

    protected final ResourceLocation uid;
    protected IDrawableStatic background;
    protected IDrawableStatic icon;
    protected ITextComponent name;

    protected IDrawableStatic energyBackground;
    protected IDrawableStatic progressBackground;
    protected IDrawableStatic progressFluidBackground;
    protected IDrawableStatic speedBackground;

    protected IDrawableAnimated energy;
    protected IDrawableAnimated progress;
    protected IDrawableAnimated progressFluid;
    protected IDrawableAnimated speed;

    public ThermalRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        this(guiHelper, uid, true);
    }

    public ThermalRecipeCategory(IGuiHelper guiHelper, ResourceLocation uid, boolean drawEnergy) {

        this.uid = uid;

        if (drawEnergy) {
            energyBackground = Drawables.getDrawables(guiHelper).getEnergyEmpty();
            energy = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getEnergyFill(), 400, IDrawableAnimated.StartDirection.TOP, true);
        }
    }

    protected void addDefaultItemTooltipCallback(IGuiItemStackGroup group, List<Float> chances, int indexOffset) {

        group.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (!chances.isEmpty() && slotIndex >= indexOffset && slotIndex < indexOffset + chances.size()) {
                float chance = Math.abs(chances.get(slotIndex - indexOffset));
                if (chance < BASE_CHANCE) {
                    tooltip.add(localize("info.cofh.chance") + ": " + (int) (100 * chance) + "%");
                } else {
                    chance -= (int) chance;
                    if (chance > 0) {
                        tooltip.add(localize("info.cofh.chance_additional") + ": " + (int) (100 * chance) + "%");
                    }
                }
            }
        });
    }

    // region IRecipeCategory
    @Override
    public ResourceLocation getUid() {

        return uid;
    }

    @Override
    public String getTitle() {

        return name.getFormattedText();
    }

    @Override
    public IDrawable getBackground() {

        return background;
    }

    @Override
    public IDrawable getIcon() {

        return icon;
    }

    @Override
    public void draw(T recipe, double mouseX, double mouseY) {

        if (energyBackground != null) {
            energyBackground.draw(ENERGY_X, ENERGY_Y);
        }
        if (energy != null) {
            energy.draw(ENERGY_X, ENERGY_Y);
        }
    }

    @Override
    public List<String> getTooltipStrings(T recipe, double mouseX, double mouseY) {

        List<String> tooltip = new ArrayList<>();

        if (energy != null && mouseX > ENERGY_X && mouseX < ENERGY_X + energy.getWidth() - 1 && mouseY > ENERGY_Y && mouseY < ENERGY_Y + energy.getHeight() - 1) {
            tooltip.add(localize("info.cofh.energy") + ": " + StringHelper.format(recipe.getEnergy()) + " RF");
        }
        return tooltip;
    }
    // endregion
}