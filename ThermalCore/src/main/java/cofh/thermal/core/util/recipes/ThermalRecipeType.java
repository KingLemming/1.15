package cofh.thermal.core.util.recipes;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.helpers.StringHelper.decompose;

public class ThermalRecipeType<T extends ThermalRecipe> implements IRecipeType<T> {

    private final ResourceLocation registryName;

    public ThermalRecipeType(String name) {

        String[] resourceLoc = decompose(ID_THERMAL, name, ':');
        this.registryName = new ResourceLocation(resourceLoc[0], resourceLoc[1]);
    }

    @Override
    public String toString() {

        return registryName.toString();
    }

    public void register() {

        Registry.register(Registry.RECIPE_TYPE, registryName, this);
    }

}
