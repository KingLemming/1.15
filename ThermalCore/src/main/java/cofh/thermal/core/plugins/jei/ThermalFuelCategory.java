package cofh.thermal.core.plugins.jei;

import cofh.thermal.core.util.recipes.ThermalFuel;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class ThermalFuelCategory<T extends ThermalFuel> implements IRecipeCategory<T> {

    protected final ResourceLocation uid;
    protected IDrawableStatic background;
    protected IDrawableStatic icon;
    protected ITextComponent name;

    protected IDrawableStatic durationBackground;

    protected IDrawableAnimated duration;

    public ThermalFuelCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        this(guiHelper, uid, true);
    }

    public ThermalFuelCategory(IGuiHelper guiHelper, ResourceLocation uid, boolean drawEnergy) {

        this.uid = uid;

        //        if (drawEnergy) {
        //            energyBackground = Drawables.getDrawables(guiHelper).getEnergyEmpty();
        //            energy = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getEnergyFill(), 400, IDrawableAnimated.StartDirection.TOP, true);
        //        }
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
    public List<String> getTooltipStrings(T recipe, double mouseX, double mouseY) {

        List<String> tooltip = new ArrayList<>();

        //        if (energy != null && mouseX > ENERGY_X && mouseX < ENERGY_X + energy.getWidth() - 1 && mouseY > ENERGY_Y && mouseY < ENERGY_Y + energy.getHeight() - 1) {
        //            tooltip.add(localize("info.cofh.energy") + ": " + StringHelper.format(recipe.getEnergy()) + " RF");
        //        }
        return tooltip;
    }
    // endregion
}
