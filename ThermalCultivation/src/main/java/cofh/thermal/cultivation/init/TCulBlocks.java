package cofh.thermal.cultivation.init;

import cofh.core.util.ProxyUtils;
import cofh.lib.block.Block4Way;
import cofh.lib.block.CakeBlockCoFH;
import cofh.lib.block.TileBlock4Way;
import cofh.lib.block.crops.StemBlockAttached;
import cofh.lib.block.crops.StemBlockCoFH;
import cofh.thermal.cultivation.block.FrostMelonBlock;
import cofh.thermal.cultivation.block.SoilBlock;
import cofh.thermal.cultivation.inventory.container.DeviceHiveExtractorContainer;
import cofh.thermal.cultivation.tileentity.DeviceHiveExtractorTile;
import net.minecraft.block.Block;
import net.minecraft.block.HayBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;

import static cofh.thermal.core.ThermalCore.*;
import static cofh.thermal.core.util.RegistrationHelper.*;
import static cofh.thermal.cultivation.init.TCulReferences.*;

public class TCulBlocks {

    private TCulBlocks() {

    }

    public static void register() {

        registerPlants();
        registerFoods();
        registerStorage();

        registerBlock(ID_PHYTOSOIL, () -> new SoilBlock(Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.8F).sound(SoundType.GROUND)));
        registerBlockOnly(ID_PHYTOSOIL_CHARGED, () -> new SoilBlock(Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.8F).sound(SoundType.GROUND).lightValue(7)));

        registerTileBlocks();
        registerTileContainers();
        registerTileEntities();
    }

    public static void setup() {

        PHYTOSOIL_BLOCK.setBoost(2);
        PHYTOSOIL_CHARGED_BLOCK.setBoost(4);
    }

    // region HELPERS
    private static void registerPlants() {

        // ANNUAL
        registerAnnual(ID_BARLEY);
        // registerTallAnnual(ID_CORN);
        registerAnnual(ID_ONION);
        registerAnnual(ID_RADISH);
        registerAnnual(ID_RICE);
        registerAnnual(ID_SADIROOT);
        registerAnnual(ID_SPINACH);

        // PERENNIAL
        registerPerennial(ID_BELL_PEPPER);
        registerPerennial(ID_EGGPLANT);
        registerPerennial(ID_GREEN_BEAN);
        registerPerennial(ID_PEANUT);
        registerPerennial(ID_STRAWBERRY);
        registerPerennial(ID_TOMATO);

        registerPerennial(ID_COFFEE);
        // registerTallPerennial(ID_HOPS);
        registerPerennial(ID_TEA);

        // STEM
        registerBlock(ID_FROST_MELON, () -> new FrostMelonBlock(Block.Properties.create(Material.GOURD, MaterialColor.CYAN).hardnessAndResistance(1.0F).sound(SoundType.SNOW).tickRandomly()), Rarity.UNCOMMON);
        registerBlockOnly(ID_FROST_MELON_STEM, () -> new StemBlockCoFH(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WOOD)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
        registerBlockOnly(ID_FROST_MELON_STEM_ATTACHED, () -> new StemBlockAttached(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.STEM)).crop(BLOCKS.getSup(ID_FROST_MELON)).seed(ITEMS.getSup(seeds(ID_FROST_MELON))));
    }

    private static void registerFoods() {

        registerBlock(ID_CHOCOLATE_CAKE, () -> new CakeBlockCoFH(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH), TCulFoods.CHOCOLATE_CAKE));
        registerBlock(ID_SPICE_CAKE, () -> new CakeBlockCoFH(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH), TCulFoods.SPICE_CAKE));
    }

    private static void registerStorage() {

        registerBlock(block(ID_BARLEY), () -> new HayBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.GOLD).hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
        registerBlock(block(ID_CORN), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_ONION), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_RADISH), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_SADIROOT), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_SPINACH), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));

        registerBlock(block(ID_BELL_PEPPER), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_EGGPLANT), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_GREEN_BEAN), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_HOPS), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_STRAWBERRY), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
        registerBlock(block(ID_TOMATO), () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));

        registerBlock(block(ID_RICE), () -> new Block4Way(Block.Properties.create(Material.WOOL, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));

        registerBlock(block(ID_COFFEE), () -> new Block4Way(Block.Properties.create(Material.WOOL, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
        registerBlock(block(ID_PEANUT), () -> new Block4Way(Block.Properties.create(Material.WOOL, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
        registerBlock(block(ID_TEA), () -> new Block4Way(Block.Properties.create(Material.WOOL, MaterialColor.GREEN_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
    }

    private static void registerTileBlocks() {

        registerBlock(ID_DEVICE_HIVE_EXTRACTOR, () -> new TileBlock4Way(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F), DeviceHiveExtractorTile::new));
    }

    private static void registerTileContainers() {

        CONTAINERS.register(ID_DEVICE_HIVE_EXTRACTOR, () -> IForgeContainerType.create((windowId, inv, data) -> new DeviceHiveExtractorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

    private static void registerTileEntities() {

        TILE_ENTITIES.register(ID_DEVICE_HIVE_EXTRACTOR, () -> TileEntityType.Builder.create(DeviceHiveExtractorTile::new, DEVICE_HIVE_EXTRACTOR_BLOCK).build(null));
    }
    // endregion
}
