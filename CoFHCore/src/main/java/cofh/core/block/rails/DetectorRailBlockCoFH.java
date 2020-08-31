package cofh.core.block.rails;

import cofh.core.block.IDismantleable;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DetectorRailBlockCoFH extends DetectorRailBlock implements IDismantleable {

    protected float maxSpeed = 0.4F;

    public DetectorRailBlockCoFH(Properties builder) {

        super(builder);
    }

    public DetectorRailBlockCoFH speed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {

        return maxSpeed;
    }

}
