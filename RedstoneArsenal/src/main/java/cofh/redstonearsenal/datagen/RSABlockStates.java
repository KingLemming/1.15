package cofh.redstonearsenal.datagen;

import cofh.core.datagen.BlockStateProviderCoFH;
import cofh.core.registries.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

import static cofh.core.util.constants.Constants.ID_REDSTONE_ARSENAL;
import static cofh.redstonearsenal.RedstoneArsenal.BLOCKS;

public class RSABlockStates extends BlockStateProviderCoFH {

    public RSABlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {

        super(gen, ID_REDSTONE_ARSENAL, exFileHelper);
    }

    @Override
    public String getName() {

        return "Redstone Arsenal: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

    }

}
