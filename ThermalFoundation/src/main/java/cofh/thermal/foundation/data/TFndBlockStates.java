package cofh.thermal.foundation.data;

import cofh.lib.data.BlockStateProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
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

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        oreBlock(reg.getSup(ID_COPPER_ORE));
        oreBlock(reg.getSup(ID_TIN_ORE));
        oreBlock(reg.getSup(ID_SILVER_ORE));
        oreBlock(reg.getSup(ID_LEAD_ORE));
        oreBlock(reg.getSup(ID_NICKEL_ORE));
        oreBlock(reg.getSup(ID_PLATINUM_ORE));

        oreBlock(reg.getSup(ID_RUBY_ORE));
        oreBlock(reg.getSup(ID_SAPPHIRE_ORE));

        storageBlock(reg.getSup(ID_COPPER_BLOCK));
        storageBlock(reg.getSup(ID_TIN_BLOCK));
        storageBlock(reg.getSup(ID_SILVER_BLOCK));
        storageBlock(reg.getSup(ID_LEAD_BLOCK));
        storageBlock(reg.getSup(ID_NICKEL_BLOCK));
        storageBlock(reg.getSup(ID_PLATINUM_BLOCK));

        storageBlock(reg.getSup(ID_BRONZE_BLOCK));
        storageBlock(reg.getSup(ID_ELECTRUM_BLOCK));
        storageBlock(reg.getSup(ID_INVAR_BLOCK));
        storageBlock(reg.getSup(ID_CONSTANTAN_BLOCK));

        storageBlock(reg.getSup(ID_RUBY_BLOCK));
        storageBlock(reg.getSup(ID_SAPPHIRE_BLOCK));
    }

}
