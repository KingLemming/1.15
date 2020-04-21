package cofh.thermal.foundation.init;

import cofh.lib.block.OreBlockCoFH;
import cofh.lib.block.storage.StorageBlock;
import net.minecraft.block.material.MaterialColor;

import static cofh.thermal.core.util.RegistrationHelper.registerBlock;
import static cofh.thermal.foundation.init.TFndReferences.*;

public class TFndBlocks {

    private TFndBlocks() {

    }

    public static void register() {

        registerOres();
        registerMetals();
        registerGems();
    }

    private static void registerOres() {

        registerBlock(ID_COPPER_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_TIN_ORE, () -> new OreBlockCoFH(1));
        registerBlock(ID_SILVER_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_LEAD_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_NICKEL_ORE, () -> new OreBlockCoFH(2));
        registerBlock(ID_PLATINUM_ORE, () -> new OreBlockCoFH(3));

        registerBlock(ID_RUBY_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
        registerBlock(ID_SAPPHIRE_ORE, () -> new OreBlockCoFH(2).xp(3, 7));
    }

    private static void registerMetals() {

        registerBlock(ID_COPPER_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_TIN_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_SILVER_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_LEAD_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_NICKEL_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_PLATINUM_BLOCK, () -> new StorageBlock(2));

        registerBlock(ID_BRONZE_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_ELECTRUM_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_INVAR_BLOCK, () -> new StorageBlock(1));
        registerBlock(ID_CONSTANTAN_BLOCK, () -> new StorageBlock(1));
    }

    private static void registerGems() {

        registerBlock(ID_RUBY_BLOCK, () -> new StorageBlock(MaterialColor.RED, 1));
        registerBlock(ID_SAPPHIRE_BLOCK, () -> new StorageBlock(MaterialColor.BLUE, 1));
    }

    private static void registerExtraMetals() {

    }

}
