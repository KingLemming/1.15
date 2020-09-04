package cofh.core.block.rails;

import cofh.core.block.IDismantleable;
import cofh.core.block.IWrenchable;
import cofh.core.util.helpers.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PoweredRailBlockCoFH extends PoweredRailBlock implements IDismantleable, IWrenchable {

    protected float maxSpeed = 0.4F;

    public PoweredRailBlockCoFH(Properties builder) {

        this(builder, false);
    }

    public PoweredRailBlockCoFH(Properties builder, boolean isActivator) {

        super(builder, isActivator);
    }

    public PoweredRailBlockCoFH speed(float maxSpeed) {

        this.maxSpeed = MathHelper.clamp(maxSpeed, 0F, 1F);
        return this;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {

        return maxSpeed;
    }

}
