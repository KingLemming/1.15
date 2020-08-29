package cofh.thermal.core.plugins.jei;

import cofh.lib.util.helpers.FluidHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.common.ThermalConfig.jeiBucketTanks;

// TODO: Implement as necessary.
// @JeiPlugin
public class ThermalJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "core");
    }

    // region HELPERS
    private RecipeManager getRecipeManager() {

        RecipeManager recipeManager = null;
        ClientWorld world = Minecraft.getInstance().world;
        if (world != null) {
            recipeManager = world.getRecipeManager();
        }
        return recipeManager;
    }

    public static void addDefaultFluidTooltipCallback(IGuiFluidStackGroup group) {

        group.addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (FluidHelper.hasPotionTag(ingredient)) {
                FluidHelper.addPotionTooltipStrings(ingredient, tooltip);
            }
        });
    }

    public static int tankSize(int size) {

        return jeiBucketTanks ? FluidAttributes.BUCKET_VOLUME : size;
    }

    public static IDrawable tankOverlay(IDrawable overlay) {

        return jeiBucketTanks ? null : overlay;
    }
    // endregion
}
