package cofh.thermal.expansion.datagen;

import cofh.lib.datagen.BlockStateProviderCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;

public class TExpBlockStates extends BlockStateProviderCoFH {

    public TExpBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {

        super(gen, ID_THERMAL, exFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

    }

}
