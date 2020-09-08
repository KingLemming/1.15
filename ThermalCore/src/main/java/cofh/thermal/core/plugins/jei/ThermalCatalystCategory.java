package cofh.thermal.core.plugins.jei;

import cofh.thermal.core.util.recipes.ThermalCatalyst;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class ThermalCatalystCategory<T extends ThermalCatalyst> implements IRecipeCategory<T> {

    protected final ResourceLocation uid;
    protected IDrawableStatic background;
    protected IDrawableStatic icon;
    protected ITextComponent name;

    public ThermalCatalystCategory(IGuiHelper guiHelper, ResourceLocation uid) {

        this(guiHelper, uid, true);
    }

    public ThermalCatalystCategory(IGuiHelper guiHelper, ResourceLocation uid, boolean drawEnergy) {

        this.uid = uid;
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

    }

    //    @Override
    //    public List<String> getTooltipStrings(T recipe, double mouseX, double mouseY) {
    //
    //        List<String> tooltip = new ArrayList<>();
    //
    //        if (energy != null && mouseX > ENERGY_X && mouseX < ENERGY_X + energy.getWidth() - 1 && mouseY > ENERGY_Y && mouseY < ENERGY_Y + energy.getHeight() - 1) {
    //            tooltip.add(localize("info.cofh.energy") + ": " + StringHelper.format(recipe.getEnergy()) + " RF");
    //        }
    //        return tooltip;
    //    }
    // endregion
}