package cofh.thermal.core.common;

import cofh.lib.item.ArmorMaterialCoFH;
import cofh.thermal.core.inventory.container.workbench.ChargeBenchContainer;
import cofh.thermal.core.inventory.container.workbench.ProjectBenchContainer;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.workbench.ChargeBenchTile;
import cofh.thermal.core.tileentity.workbench.ProjectBenchTile;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class ThermalReferences {

    private ThermalReferences() {

    }

    // region ARMOR & TOOLS
    public static final String ID_BEEKEEPER_HELMET = "beekeeper_helmet";
    public static final String ID_BEEKEEPER_CHESTPLATE = "beekeeper_chestplate";
    public static final String ID_BEEKEEPER_LEGGINGS = "beekeeper_leggings";
    public static final String ID_BEEKEEPER_BOOTS = "beekeeper_boots";

    public static final String ID_HAZMAT_HELMET = "hazmat_helmet";
    public static final String ID_HAZMAT_CHESTPLATE = "hazmat_chestplate";
    public static final String ID_HAZMAT_LEGGINGS = "hazmat_leggings";
    public static final String ID_HAZMAT_BOOTS = "hazmat_boots";

    public static final ArmorMaterialCoFH BEEKEEPER = new ArmorMaterialCoFH("thermal:beekeeper", 3, new int[]{1, 1, 1, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.0F, () -> Ingredient.fromItems(Items.STRING));
    public static final ArmorMaterialCoFH HAZMAT = new ArmorMaterialCoFH("thermal:hazmat", 4, new int[]{1, 2, 3, 1}, 18, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(Items.STRING));
    // endregion

    // region WORKBENCHES
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
}
