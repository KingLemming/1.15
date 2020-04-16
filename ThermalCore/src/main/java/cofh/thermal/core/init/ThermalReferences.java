package cofh.thermal.core.init;

import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class ThermalReferences {

    private ThermalReferences() {

    }

    public static final String ID_SULFUR_ORE = "sulfur_ore";
    public static final String ID_NITER_ORE = "niter_ore";

    public static final String ID_SIGNALUM_BLOCK = "signalum_block";
    public static final String ID_LUMIUM_BLOCK = "lumium_block";
    public static final String ID_ENDERIUM_BLOCK = "enderium_block";

    public static final String ID_REDSTONE_FLUID = "fluid_redstone";
    public static final String ID_GLOWSTONE_FLUID = "fluid_glowstone";
    public static final String ID_ENDER_FLUID = "fluid_ender";

    public static final String ID_WRENCH = "wrench";

    public static final String ID_SULFUR_DUST = "sulfur_dust";
    public static final String ID_NITER_DUST = "niter_dust";

    // region RECIPES
    public static final ResourceLocation ID_RECIPE_FURNACE = new ResourceLocation(ID_THERMAL, "furnace");
    public static final ResourceLocation ID_RECIPE_SAWMILL = new ResourceLocation(ID_THERMAL, "sawmill");
    public static final ResourceLocation ID_RECIPE_PULVERIZER = new ResourceLocation(ID_THERMAL, "pulverizer");
    public static final ResourceLocation ID_RECIPE_INSOLATOR = new ResourceLocation(ID_THERMAL, "insolator");
    public static final ResourceLocation ID_RECIPE_CENTRIFUGE = new ResourceLocation(ID_THERMAL, "centrifuge");
    public static final ResourceLocation ID_RECIPE_PRESS = new ResourceLocation(ID_THERMAL, "press");
    public static final ResourceLocation ID_RECIPE_CRUCIBLE = new ResourceLocation(ID_THERMAL, "crucible");
    public static final ResourceLocation ID_RECIPE_CHILLER = new ResourceLocation(ID_THERMAL, "chiller");
    public static final ResourceLocation ID_RECIPE_REFINERY = new ResourceLocation(ID_THERMAL, "refinery");
    public static final ResourceLocation ID_RECIPE_BREWER = new ResourceLocation(ID_THERMAL, "brewer");

    public static final ResourceLocation ID_RECIPE_BOTTLER = new ResourceLocation(ID_THERMAL, "bottler");
    public static final ResourceLocation ID_CATALYST_PULVERIZER = new ResourceLocation(ID_THERMAL, "pulverizer_catalyst");
    public static final ResourceLocation ID_CATALYST_INSOLATOR = new ResourceLocation(ID_THERMAL, "insolator_catalyst");
    // endregion

    // region FUELS
    public static final ResourceLocation ID_FUEL_STIRLING = new ResourceLocation(ID_THERMAL, "stirling_fuel");
    public static final ResourceLocation ID_FUEL_COMPRESSION = new ResourceLocation(ID_THERMAL, "compression_fuel");
    public static final ResourceLocation ID_FUEL_MAGMATIC = new ResourceLocation(ID_THERMAL, "magmatic_fuel");
    public static final ResourceLocation ID_FUEL_NUMISMATIC = new ResourceLocation(ID_THERMAL, "numismatic_fuel");
    public static final ResourceLocation ID_FUEL_LAPIDARY = new ResourceLocation(ID_THERMAL, "lapidary_fuel");
    // endregion
}
