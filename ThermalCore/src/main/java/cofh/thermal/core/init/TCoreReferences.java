package cofh.thermal.core.init;

import cofh.lib.item.ArmorMaterialCoFH;
import cofh.thermal.core.entity.item.*;
import cofh.thermal.core.entity.monster.BasalzEntity;
import cofh.thermal.core.entity.monster.BlitzEntity;
import cofh.thermal.core.entity.monster.BlizzEntity;
import cofh.thermal.core.entity.projectile.*;
import cofh.thermal.core.inventory.container.device.DeviceHiveExtractorContainer;
import cofh.thermal.core.inventory.container.device.DeviceTreeExtractorContainer;
import cofh.thermal.core.inventory.container.workbench.ChargeBenchContainer;
import cofh.thermal.core.inventory.container.workbench.ProjectBenchContainer;
import cofh.thermal.core.inventory.container.workbench.TinkerBenchContainer;
import cofh.thermal.core.tileentity.device.DeviceHiveExtractorTile;
import cofh.thermal.core.tileentity.device.DeviceTreeExtractorTile;
import cofh.thermal.core.tileentity.workbench.ChargeBenchTile;
import cofh.thermal.core.tileentity.workbench.ProjectBenchTile;
import cofh.thermal.core.tileentity.workbench.TinkerBenchTile;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class TCoreReferences {

    private TCoreReferences() {

    }

    // region VANILLA
    public static final String ID_CHARCOAL_BLOCK = "charcoal_block";
    public static final String ID_BAMBOO_BLOCK = "bamboo_block";
    public static final String ID_SUGAR_CANE_BLOCK = "sugar_cane_block";
    public static final String ID_GUNPOWDER_BLOCK = "gunpowder_block";

    public static final String ID_APPLE_BLOCK = "apple_block";
    public static final String ID_BEETROOT_BLOCK = "beetroot_block";
    public static final String ID_CARROT_BLOCK = "carrot_block";
    public static final String ID_POTATO_BLOCK = "potato_block";
    // endregion

    // region RESOURCES
    public static final String ID_APATITE_ORE = "apatite_ore";
    public static final String ID_CINNABAR_ORE = "cinnabar_ore";
    public static final String ID_NITER_ORE = "niter_ore";
    public static final String ID_SULFUR_ORE = "sulfur_ore";

    public static final String ID_COPPER_ORE = "copper_ore";
    public static final String ID_LEAD_ORE = "lead_ore";
    public static final String ID_NICKEL_ORE = "nickel_ore";
    public static final String ID_SILVER_ORE = "silver_ore";
    public static final String ID_TIN_ORE = "tin_ore";

    public static final String ID_RUBY_ORE = "ruby_ore";
    public static final String ID_SAPPHIRE_ORE = "sapphire_ore";
    // endregion

    // region STORAGE
    public static final String ID_SAWDUST_BLOCK = "sawdust_block";
    public static final String ID_ROSIN_BLOCK = "rosin_block";
    public static final String ID_RUBBER_BLOCK = "rubber_block";
    public static final String ID_CURED_RUBBER_BLOCK = "cured_rubber_block";
    public static final String ID_SLAG_BLOCK = "slag_block";
    public static final String ID_RICH_SLAG_BLOCK = "rich_slag_block";

    public static final String ID_APATITE_BLOCK = "apatite_block";
    public static final String ID_CINNABAR_BLOCK = "cinnabar_block";
    public static final String ID_NITER_BLOCK = "niter_block";
    public static final String ID_SULFUR_BLOCK = "sulfur_block";

    public static final String ID_COPPER_BLOCK = "copper_block";
    public static final String ID_LEAD_BLOCK = "lead_block";
    public static final String ID_NICKEL_BLOCK = "nickel_block";
    public static final String ID_SILVER_BLOCK = "silver_block";
    public static final String ID_TIN_BLOCK = "tin_block";

    public static final String ID_BRONZE_BLOCK = "bronze_block";
    public static final String ID_CONSTANTAN_BLOCK = "constantan_block";
    public static final String ID_ELECTRUM_BLOCK = "electrum_block";
    public static final String ID_INVAR_BLOCK = "invar_block";

    public static final String ID_RUBY_BLOCK = "ruby_block";
    public static final String ID_SAPPHIRE_BLOCK = "sapphire_block";

    public static final String ID_ENDERIUM_BLOCK = "enderium_block";
    public static final String ID_LUMIUM_BLOCK = "lumium_block";
    public static final String ID_SIGNALUM_BLOCK = "signalum_block";
    // endregion

    // region BUILDING BLOCKS
    public static final String ID_MACHINE_FRAME = "machine_frame";

    public static final String ID_ENDERIUM_GLASS = "enderium_glass";
    public static final String ID_LUMIUM_GLASS = "lumium_glass";
    public static final String ID_SIGNALUM_GLASS = "signalum_glass";
    // endregion

    // region FLUIDS
    public static final String ID_FLUID_SEED_OIL = "seed_oil";
    public static final String ID_FLUID_BIOCRUDE = "biocrude";
    public static final String ID_FLUID_BIOFUEL = "biofuel";

    public static final String ID_FLUID_LATEX = "latex";
    public static final String ID_FLUID_RESIN = "resin";
    public static final String ID_FLUID_TREE_OIL = "tree_oil";
    public static final String ID_FLUID_SAP = "sap";
    public static final String ID_FLUID_SYRUP = "syrup";

    public static final String ID_FLUID_REDSTONE = "redstone";
    public static final String ID_FLUID_GLOWSTONE = "glowstone";
    public static final String ID_FLUID_ENDER = "ender";
    // endregion

    // region ENTITIES
    public static final String ID_BASALZ = ID_THERMAL + ":basalz";
    public static final String ID_BLITZ = ID_THERMAL + ":blitz";
    public static final String ID_BLIZZ = ID_THERMAL + ":blizz";

    public static final String ID_BASALZ_PROJECTILE = ID_THERMAL + ":basalz_projectile";
    public static final String ID_BLITZ_PROJECTILE = ID_THERMAL + ":blitz_projectile";
    public static final String ID_BLIZZ_PROJECTILE = ID_THERMAL + ":blizz_projectile";

    public static final String ID_EXPLOSIVE_GRENADE = ID_THERMAL + ":grenade";
    public static final String ID_PHYTO_GRENADE = ID_THERMAL + ":phyto_grenade";

    public static final String ID_FIRE_GRENADE = ID_THERMAL + ":fire_grenade";
    public static final String ID_EARTH_GRENADE = ID_THERMAL + ":earth_grenade";
    public static final String ID_ICE_GRENADE = ID_THERMAL + ":ice_grenade";
    public static final String ID_LIGHTNING_GRENADE = ID_THERMAL + ":lightning_grenade";

    public static final String ID_PHYTO_TNT = ID_THERMAL + ":phyto_tnt";

    public static final String ID_FIRE_TNT = ID_THERMAL + ":fire_tnt";
    public static final String ID_EARTH_TNT = ID_THERMAL + ":earth_tnt";
    public static final String ID_ICE_TNT = ID_THERMAL + ":ice_tnt";
    public static final String ID_LIGHTNING_TNT = ID_THERMAL + ":lightning_tnt";

    @ObjectHolder(ID_BASALZ)
    public static final EntityType<BasalzEntity> BASALZ_ENTITY = null;
    @ObjectHolder(ID_BLITZ)
    public static final EntityType<BlitzEntity> BLITZ_ENTITY = null;
    @ObjectHolder(ID_BLIZZ)
    public static final EntityType<BlizzEntity> BLIZZ_ENTITY = null;

    @ObjectHolder(ID_BASALZ_PROJECTILE)
    public static final EntityType<BasalzProjectileEntity> BASALZ_PROJECTILE_ENTITY = null;
    @ObjectHolder(ID_BLITZ_PROJECTILE)
    public static final EntityType<BlitzProjectileEntity> BLITZ_PROJECTILE_ENTITY = null;
    @ObjectHolder(ID_BLIZZ_PROJECTILE)
    public static final EntityType<BlizzProjectileEntity> BLIZZ_PROJECTILE_ENTITY = null;

    @ObjectHolder(ID_EXPLOSIVE_GRENADE)
    public static final Item EXPLOSIVE_GRENADE_ITEM = null;
    @ObjectHolder(ID_PHYTO_GRENADE)
    public static final Item PHYTO_GRENADE_ITEM = null;

    @ObjectHolder(ID_FIRE_GRENADE)
    public static final Item FIRE_GRENADE_ITEM = null;
    @ObjectHolder(ID_EARTH_GRENADE)
    public static final Item EARTH_GRENADE_ITEM = null;
    @ObjectHolder(ID_ICE_GRENADE)
    public static final Item ICE_GRENADE_ITEM = null;
    @ObjectHolder(ID_LIGHTNING_GRENADE)
    public static final Item LIGHTNING_GRENADE_ITEM = null;

    @ObjectHolder(ID_EXPLOSIVE_GRENADE)
    public static final EntityType<ExplosiveGrenadeEntity> EXPLOSIVE_GRENADE_ENTITY = null;
    @ObjectHolder(ID_PHYTO_GRENADE)
    public static final EntityType<PhytoGrenadeEntity> PHYTO_GRENADE_ENTITY = null;

    @ObjectHolder(ID_FIRE_GRENADE)
    public static final EntityType<FireGrenadeEntity> FIRE_GRENADE_ENTITY = null;
    @ObjectHolder(ID_EARTH_GRENADE)
    public static final EntityType<EarthGrenadeEntity> EARTH_GRENADE_ENTITY = null;
    @ObjectHolder(ID_ICE_GRENADE)
    public static final EntityType<IceGrenadeEntity> ICE_GRENADE_ENTITY = null;
    @ObjectHolder(ID_LIGHTNING_GRENADE)
    public static final EntityType<LightningGrenadeEntity> LIGHTNING_GRENADE_ENTITY = null;

    @ObjectHolder(ID_PHYTO_TNT)
    public static final EntityType<PhytoTNTEntity> PHYTO_TNT_ENTITY = null;

    @ObjectHolder(ID_FIRE_TNT)
    public static final EntityType<FireTNTEntity> FIRE_TNT_ENTITY = null;
    @ObjectHolder(ID_EARTH_TNT)
    public static final EntityType<EarthTNTEntity> EARTH_TNT_ENTITY = null;
    @ObjectHolder(ID_ICE_TNT)
    public static final EntityType<IceTNTEntity> ICE_TNT_ENTITY = null;
    @ObjectHolder(ID_LIGHTNING_TNT)
    public static final EntityType<LightningTNTEntity> LIGHTNING_TNT_ENTITY = null;
    // endregion

    // region SOUND EVENTS
    public static final String ID_SOUND_BASALZ_AMBIENT = ID_THERMAL + ":mob.basalz.ambient";
    public static final String ID_SOUND_BASALZ_DEATH = ID_THERMAL + ":mob.basalz.death";
    public static final String ID_SOUND_BASALZ_HURT = ID_THERMAL + ":mob.basalz.hurt";
    public static final String ID_SOUND_BASALZ_ROAM = ID_THERMAL + ":mob.basalz.roam";
    public static final String ID_SOUND_BASALZ_SHOOT = ID_THERMAL + ":mob.basalz.shoot";

    public static final String ID_SOUND_BLITZ_AMBIENT = ID_THERMAL + ":mob.blitz.ambient";
    public static final String ID_SOUND_BLITZ_DEATH = ID_THERMAL + ":mob.blitz.death";
    public static final String ID_SOUND_BLITZ_HURT = ID_THERMAL + ":mob.blitz.hurt";
    public static final String ID_SOUND_BLITZ_ROAM = ID_THERMAL + ":mob.blitz.roam";
    public static final String ID_SOUND_BLITZ_SHOOT = ID_THERMAL + ":mob.blitz.shoot";

    public static final String ID_SOUND_BLIZZ_AMBIENT = ID_THERMAL + ":mob.blizz.ambient";
    public static final String ID_SOUND_BLIZZ_DEATH = ID_THERMAL + ":mob.blizz.death";
    public static final String ID_SOUND_BLIZZ_HURT = ID_THERMAL + ":mob.blizz.hurt";
    public static final String ID_SOUND_BLIZZ_ROAM = ID_THERMAL + ":mob.blizz.roam";
    public static final String ID_SOUND_BLIZZ_SHOOT = ID_THERMAL + ":mob.blizz.shoot";

    public static final String ID_SOUND_MAGNET = ID_THERMAL + ":item.magnet";
    public static final String ID_SOUND_TINKER = ID_THERMAL + ":misc.tinker";
    // endregion

    // region ARMOR & TOOLS
    public static final String ID_BEEKEEPER_HELMET = "beekeeper_helmet";
    public static final String ID_BEEKEEPER_CHESTPLATE = "beekeeper_chestplate";
    public static final String ID_BEEKEEPER_LEGGINGS = "beekeeper_leggings";
    public static final String ID_BEEKEEPER_BOOTS = "beekeeper_boots";

    public static final String ID_DIVING_HELMET = "diving_helmet";
    public static final String ID_DIVING_CHESTPLATE = "diving_chestplate";
    public static final String ID_DIVING_LEGGINGS = "diving_leggings";
    public static final String ID_DIVING_BOOTS = "diving_boots";

    public static final String ID_HAZMAT_HELMET = "hazmat_helmet";
    public static final String ID_HAZMAT_CHESTPLATE = "hazmat_chestplate";
    public static final String ID_HAZMAT_LEGGINGS = "hazmat_leggings";
    public static final String ID_HAZMAT_BOOTS = "hazmat_boots";

    public static final ArmorMaterialCoFH BEEKEEPER = new ArmorMaterialCoFH("thermal:beekeeper", 3, new int[]{1, 2, 3, 1}, 16, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, 0.0F, () -> Ingredient.fromItems(ITEMS.get("beekeeper_fabric")));
    public static final ArmorMaterialCoFH DIVING = new ArmorMaterialCoFH("thermal:diving", 12, new int[]{1, 4, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, () -> Ingredient.fromItems(ITEMS.get("diving_fabric")));
    public static final ArmorMaterialCoFH HAZMAT = new ArmorMaterialCoFH("thermal:hazmat", 4, new int[]{1, 4, 5, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, () -> Ingredient.fromItems(ITEMS.get("hazmat_fabric")));
    // endregion

    // region DEVICES
    public static final String ID_DEVICE_HIVE_EXTRACTOR = ID_THERMAL + ":device_hive_extractor";
    public static final String ID_DEVICE_TREE_EXTRACTOR = ID_THERMAL + ":device_tree_extractor";

    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static final Block DEVICE_HIVE_EXTRACTOR_BLOCK = null;
    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static final TileEntityType<DeviceHiveExtractorTile> DEVICE_HIVE_EXTRACTOR_TILE = null;
    @ObjectHolder(ID_DEVICE_HIVE_EXTRACTOR)
    public static final ContainerType<DeviceHiveExtractorContainer> DEVICE_HIVE_EXTRACTOR_CONTAINER = null;

    @ObjectHolder(ID_DEVICE_TREE_EXTRACTOR)
    public static final Block DEVICE_TREE_EXTRACTOR_BLOCK = null;
    @ObjectHolder(ID_DEVICE_TREE_EXTRACTOR)
    public static final TileEntityType<DeviceTreeExtractorTile> DEVICE_TREE_EXTRACTOR_TILE = null;
    @ObjectHolder(ID_DEVICE_TREE_EXTRACTOR)
    public static final ContainerType<DeviceTreeExtractorContainer> DEVICE_TREE_EXTRACTOR_CONTAINER = null;
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
