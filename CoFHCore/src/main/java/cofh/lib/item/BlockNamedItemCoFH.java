package cofh.lib.item;

import net.minecraft.block.Block;

public class BlockNamedItemCoFH extends BlockItemCoFH {

    public BlockNamedItemCoFH(Block blockIn, Properties properties) {

        super(blockIn, properties);
    }

    public String getTranslationKey() {

        return getDefaultTranslationKey();
    }

}
