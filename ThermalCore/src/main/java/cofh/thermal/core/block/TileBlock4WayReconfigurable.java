package cofh.thermal.core.block;

import cofh.lib.block.TileBlock4Way;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

import javax.annotation.Nullable;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.FACING_HORIZONTAL;
import static cofh.lib.util.control.IReconfigurable.*;
import static cofh.lib.util.control.IReconfigurable.SideConfig.*;

public class TileBlock4WayReconfigurable extends TileBlock4Way {

    public TileBlock4WayReconfigurable(Properties builder, Supplier<? extends TileEntity> supplier) {

        super(builder, supplier);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(ACTIVE, false)
                .with(RECONFIG_NORTH, SIDE_NONE)
                .with(RECONFIG_EAST, SIDE_INPUT)
                .with(RECONFIG_SOUTH, SIDE_OUTPUT)
                .with(RECONFIG_WEST, SIDE_BOTH)
                .with(RECONFIG_UP, SIDE_OUTPUT)
                .with(RECONFIG_DOWN, SIDE_OUTPUT)
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {

        super.fillStateContainer(builder);
        builder.add(RECONFIG_NORTH);
        builder.add(RECONFIG_EAST);
        builder.add(RECONFIG_SOUTH);
        builder.add(RECONFIG_WEST);
        builder.add(RECONFIG_UP);
        builder.add(RECONFIG_DOWN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        BlockState state = super.getStateForPlacement(context);
        // TODO: Check ItemStack
        System.out.println(state);
        return state;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {

        switch (rot) {
            case CLOCKWISE_90:
                return state.with(FACING_HORIZONTAL, rot.rotate(state.get(FACING_HORIZONTAL)))
                        .with(RECONFIG_NORTH, state.get(RECONFIG_WEST))
                        .with(RECONFIG_EAST, state.get(RECONFIG_NORTH))
                        .with(RECONFIG_SOUTH, state.get(RECONFIG_EAST))
                        .with(RECONFIG_WEST, state.get(RECONFIG_SOUTH));
            case CLOCKWISE_180:
                return state.with(FACING_HORIZONTAL, rot.rotate(state.get(FACING_HORIZONTAL)))
                        .with(RECONFIG_NORTH, state.get(RECONFIG_SOUTH))
                        .with(RECONFIG_EAST, state.get(RECONFIG_WEST))
                        .with(RECONFIG_SOUTH, state.get(RECONFIG_NORTH))
                        .with(RECONFIG_WEST, state.get(RECONFIG_EAST));
            default:
                return state.with(FACING_HORIZONTAL, rot.rotate(state.get(FACING_HORIZONTAL)))
                        .with(RECONFIG_NORTH, state.get(RECONFIG_EAST))
                        .with(RECONFIG_EAST, state.get(RECONFIG_SOUTH))
                        .with(RECONFIG_SOUTH, state.get(RECONFIG_WEST))
                        .with(RECONFIG_WEST, state.get(RECONFIG_NORTH));
        }
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {

        return rotate(state, Rotation.CLOCKWISE_180);
    }

}
