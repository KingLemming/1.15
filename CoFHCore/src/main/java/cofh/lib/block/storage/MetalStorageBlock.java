package cofh.lib.block.storage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class MetalStorageBlock extends Block {

    public MetalStorageBlock(int harvestLevel) {

        this(Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE));
    }

    public MetalStorageBlock(MaterialColor color, int harvestLevel) {

        this(Properties.create(Material.IRON, color).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestLevel(harvestLevel).harvestTool(ToolType.PICKAXE));
    }

    public MetalStorageBlock(Properties builder) {

        super(builder);
    }

    @Override
    public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {

        return true;
    }

}
