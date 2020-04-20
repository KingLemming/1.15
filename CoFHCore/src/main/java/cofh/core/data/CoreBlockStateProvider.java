package cofh.core.data;

import cofh.lib.data.BlockStateProviderCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_COFH_CORE;

public class CoreBlockStateProvider extends BlockStateProviderCoFH {

    public CoreBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {

        super(gen, ID_COFH_CORE, exFileHelper);
    }

    @Override
    public String getName() {

        return "CoFH Core: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        // simpleBlock(BLOCKS.getSup(ID_GLOSSED_MAGMA));
    }

}
