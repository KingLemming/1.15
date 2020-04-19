package cofh.thermal.foundation.data;

import cofh.lib.data.BlockStateProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.foundation.init.TFndReferences.*;

public class TFndBlockStates extends BlockStateProviderCoFH {

    public TFndBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {

        super(gen, ID_THERMAL, exFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Foundation: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        oreBlock(BLOCKS.getSup(ID_COPPER_ORE));
        oreBlock(BLOCKS.getSup(ID_TIN_ORE));
        oreBlock(BLOCKS.getSup(ID_SILVER_ORE));
        oreBlock(BLOCKS.getSup(ID_LEAD_ORE));
        oreBlock(BLOCKS.getSup(ID_NICKEL_ORE));
        oreBlock(BLOCKS.getSup(ID_PLATINUM_ORE));

        oreBlock(BLOCKS.getSup(ID_RUBY_ORE));
        oreBlock(BLOCKS.getSup(ID_SAPPHIRE_ORE));

        storageBlock(BLOCKS.getSup(ID_COPPER_BLOCK));
        storageBlock(BLOCKS.getSup(ID_TIN_BLOCK));
        storageBlock(BLOCKS.getSup(ID_SILVER_BLOCK));
        storageBlock(BLOCKS.getSup(ID_LEAD_BLOCK));
        storageBlock(BLOCKS.getSup(ID_NICKEL_BLOCK));
        storageBlock(BLOCKS.getSup(ID_PLATINUM_BLOCK));

        storageBlock(BLOCKS.getSup(ID_BRONZE_BLOCK));
        storageBlock(BLOCKS.getSup(ID_ELECTRUM_BLOCK));
        storageBlock(BLOCKS.getSup(ID_INVAR_BLOCK));
        storageBlock(BLOCKS.getSup(ID_CONSTANTAN_BLOCK));

        storageBlock(BLOCKS.getSup(ID_RUBY_BLOCK));
        storageBlock(BLOCKS.getSup(ID_SAPPHIRE_BLOCK));
    }

}
