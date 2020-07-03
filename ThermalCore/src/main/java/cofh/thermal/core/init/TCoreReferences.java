package cofh.thermal.core.init;

import cofh.thermal.core.inventory.container.workbench.ChargeBenchContainer;
import cofh.thermal.core.inventory.container.workbench.ProjectBenchContainer;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.workbench.ChargeBenchTile;
import cofh.thermal.core.tileentity.workbench.ProjectBenchTile;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class TCoreReferences {

    private TCoreReferences() {

    }

    public static final String ID_CHARCOAL_BLOCK = "charcoal_block";
    public static final String ID_BAMBOO_BLOCK = "bamboo_block";
    public static final String ID_SUGAR_CANE_BLOCK = "sugar_cane_block";
    public static final String ID_GUNPOWDER_BLOCK = "gunpowder_block";

    public static final String ID_APATITE_ORE = "apatite_ore";
    public static final String ID_CINNABAR_ORE = "cinnabar_ore";
    public static final String ID_NITER_ORE = "niter_ore";
    public static final String ID_SULFUR_ORE = "sulfur_ore";

    public static final String ID_APATITE_BLOCK = "apatite_block";
    public static final String ID_CINNABAR_BLOCK = "cinnabar_block";
    public static final String ID_NITER_BLOCK = "niter_block";
    public static final String ID_SULFUR_BLOCK = "sulfur_block";

    public static final String ID_SIGNALUM_BLOCK = "signalum_block";
    public static final String ID_LUMIUM_BLOCK = "lumium_block";
    public static final String ID_ENDERIUM_BLOCK = "enderium_block";

    public static final String ID_SIGNALUM_GLASS = "signalum_glass";
    public static final String ID_LUMIUM_GLASS = "lumium_glass";
    public static final String ID_ENDERIUM_GLASS = "enderium_glass";

    public static final String ID_REDSTONE_FLUID = "fluid_redstone";
    public static final String ID_GLOWSTONE_FLUID = "fluid_glowstone";
    public static final String ID_ENDER_FLUID = "fluid_ender";

    // region ARMOR & TOOLS
    public static final String ID_BEEKEEPER_HELMET = "beekeeper_helmet";
    public static final String ID_BEEKEEPER_CHESTPLATE = "beekeeper_chestplate";
    public static final String ID_BEEKEEPER_LEGGINGS = "beekeeper_leggings";
    public static final String ID_BEEKEEPER_BOOTS = "beekeeper_boots";

    public static final String ID_HAZMAT_HELMET = "hazmat_helmet";
    public static final String ID_HAZMAT_CHESTPLATE = "hazmat_chestplate";
    public static final String ID_HAZMAT_LEGGINGS = "hazmat_leggings";
    public static final String ID_HAZMAT_BOOTS = "hazmat_boots";

    public static final String ID_REDPRINT = "redprint";
    public static final String ID_WRENCH = "wrench";
    // endregion

    // region CRAFTING STATIONS
    public static final String ID_CHARGE_BENCH = ID_THERMAL + ":charge_bench";
    public static final String ID_PROJECT_BENCH = ID_THERMAL + ":project_bench";
    public static final String ID_TINKER_BENCH = ID_THERMAL + ":tinker_bench";

    @ObjectHolder(ID_CHARGE_BENCH)
    public static final Block CHARGE_BENCH_BLOCK = null;
    @ObjectHolder(ID_CHARGE_BENCH)
    public static final TileEntityType<ChargeBenchTile> CHARGE_BENCH_TILE = null;
    @ObjectHolder(ID_CHARGE_BENCH)
    public static final ContainerType<ChargeBenchContainer> CHARGE_BENCH_CONTAINER = null;

    @ObjectHolder(ID_PROJECT_BENCH)
    public static final Block PROJECT_BENCH_BLOCK = null;
    @ObjectHolder(ID_PROJECT_BENCH)
    public static final TileEntityType<ProjectBenchTile> PROJECT_BENCH_TILE = null;
    @ObjectHolder(ID_PROJECT_BENCH)
    public static final ContainerType<ProjectBenchContainer> PROJECT_BENCH_CONTAINER = null;

    @ObjectHolder(ID_TINKER_BENCH)
    public static final Block TINKER_BENCH_BLOCK = null;
    @ObjectHolder(ID_TINKER_BENCH)
    public static final TileEntityType<TinkerBenchTile> TINKER_BENCH_TILE = null;
    @ObjectHolder(ID_TINKER_BENCH)
    public static final ContainerType<TinkerBenchContainer> TINKER_BENCH_CONTAINER = null;
    // endregion

    // region ENTITIES
    public static final String ID_BASALZ = ID_THERMAL + ":basalz";
    public static final String ID_BLITZ = ID_THERMAL + ":blitz";
    public static final String ID_BLIZZ = ID_THERMAL + ":blizz";
    // endregion

    // region SOUND EVENTS
    public static final String ID_SOUND_TINKER = ID_THERMAL + ":misc.tinker";

    @ObjectHolder(ID_SOUND_TINKER)
    public static final SoundEvent SOUND_TINKER = null;
    // endregion
}
