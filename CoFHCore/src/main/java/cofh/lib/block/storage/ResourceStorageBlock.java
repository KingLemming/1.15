package cofh.lib.block.storage;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class ResourceStorageBlock extends Block {

    public ResourceStorageBlock() {

        this(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE).harvestLevel(0).harvestTool(ToolType.PICKAXE));
    }

    public ResourceStorageBlock(Properties properties) {

        super(properties);
    }

}
