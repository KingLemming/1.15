package cofh.thermal.core.init;

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
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.init.TCoreIDs.*;

@ObjectHolder(ID_THERMAL)
public class TCoreReferences {

    private TCoreReferences() {

    }

    // region ENTITIES
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

    @ObjectHolder(ID_NUKE_GRENADE)
    public static final EntityType<LightningGrenadeEntity> NUKE_GRENADE_ENTITY = null;

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

    @ObjectHolder(ID_NUKE_TNT)
    public static final EntityType<NukeTNTEntity> NUKE_TNT_ENTITY = null;
    // endregion

    // region ITEMS
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

    @ObjectHolder(ID_NUKE_GRENADE)
    public static final Item NUKE_GRENADE_ITEM = null;
    // endregion

    // region DEVICES
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
