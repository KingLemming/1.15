package cofh.lib.block.ores;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class OreBlockFluid extends Block {

    public OreBlockFluid() {

        this(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }

    public OreBlockFluid(Properties builder) {

        super(builder);
    }

}
