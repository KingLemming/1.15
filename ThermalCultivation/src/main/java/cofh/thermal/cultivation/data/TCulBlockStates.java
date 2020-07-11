package cofh.thermal.cultivation.data;

import cofh.lib.data.BlockStateProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.util.RegistrationHelper.block;
import static cofh.thermal.cultivation.init.TCulReferences.ID_BARLEY;

public class TCulBlockStates extends BlockStateProviderCoFH {

    public TCulBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {

        super(gen, ID_THERMAL, exFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Cultivation: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        axisBlock(reg.getSup(block(ID_BARLEY)), "barley_block", STORAGE);
    }

}
