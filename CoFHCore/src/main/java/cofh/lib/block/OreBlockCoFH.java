package cofh.lib.block;

import cofh.lib.util.helpers.MathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class OreBlockCoFH extends Block {

    protected int minXP = 0;
    protected int maxXP = 0;

    public OreBlockCoFH(int harvestLevel) {

        this(Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }

    public OreBlockCoFH(MaterialColor color, int harvestLevel) {

        this(Properties.create(Material.ROCK, color).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE));
    }

    public OreBlockCoFH(Properties properties) {

        super(properties);
    }

    public OreBlockCoFH xp(int minXP, int maxXP) {

        this.minXP = minXP;
        this.maxXP = maxXP;
        return this;
    }

    protected int getExperience() {

        if (maxXP <= 0) {
            return 0;
        }
        if (minXP >= maxXP) {
            return minXP;
        }
        return MathHelper.nextInt(minXP, maxXP);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {

        return silktouch == 0 ? getExperience() : 0;
    }

}
