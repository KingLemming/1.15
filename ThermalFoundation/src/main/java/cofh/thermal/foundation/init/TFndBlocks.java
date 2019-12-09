package cofh.thermal.foundation.init;

import cofh.lib.block.OreBlockCoFH;
import cofh.lib.block.storage.MetalStorageBlock;
import net.minecraft.block.material.MaterialColor;

import static cofh.thermal.core.init.ThermalReferences.*;
import static cofh.thermal.core.util.RegistrationHelper.registerBlock;

public class TFndBlocks {

    private TFndBlocks() {

    }

    public static void register() {

        registerThermalOres();
        registerThermalMetals();
        registerThermalGems();
    }

    private static void registerThermalOres() {

        registerBlock(ID_COPPER_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_TIN_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_SILVER_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_LEAD_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_NICKEL_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_PLATINUM_ORE, () -> new OreBlockCoFH(2));

        registerBlock(ID_RUBY_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
        registerBlock(ID_SAPPHIRE_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
    }

    private static void registerThermalMetals() {

        registerBlock(ID_COPPER_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_TIN_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_SILVER_BLOCK, () -> new MetalStorageBlock(2));
        registerBlock(ID_LEAD_BLOCK, () -> new MetalStorageBlock(2));
        registerBlock(ID_NICKEL_BLOCK, () -> new MetalStorageBlock(2));
        registerBlock(ID_PLATINUM_BLOCK, () -> new MetalStorageBlock(2));

        registerBlock(ID_BRONZE_BLOCK, () -> new MetalStorageBlock(1));
        registerBlock(ID_ELECTRUM_BLOCK, () -> new MetalStorageBlock(2));
        registerBlock(ID_INVAR_BLOCK, () -> new MetalStorageBlock(2));
        registerBlock(ID_CONSTANTAN_BLOCK, () -> new MetalStorageBlock(2));
    }

    private static void registerThermalGems() {

        registerBlock(ID_RUBY_BLOCK, () -> new MetalStorageBlock(MaterialColor.RED, 2));
        registerBlock(ID_SAPPHIRE_BLOCK, () -> new MetalStorageBlock(MaterialColor.BLUE, 2));
    }

    private static void registerExtraMetals() {

    }

}
