package cofh.lib.block.storage;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class ResourceStorageBlock extends Block {

    public ResourceStorageBlock(int harvestLevel) {

        this(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE).harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE));
    }

    public ResourceStorageBlock(MaterialColor color, int harvestLevel) {

        this(Properties.create(Material.ROCK, color).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE).harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE));
    }

    public ResourceStorageBlock(Properties builder) {

        super(builder);
    }

}
