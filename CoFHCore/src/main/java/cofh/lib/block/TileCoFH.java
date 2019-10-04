package cofh.lib.block;

import cofh.lib.util.control.ISecurable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileCoFH extends TileEntity implements ITileCallback {

    public TileCoFH(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    // region PASSTHROUGHS

    // endregion

    // region HELPERS
    protected void updateLighting() {

//        int light = world.getLightFor(EnumSkyBlock.BLOCK, pos);
//        if (getLightValue() != light && world.checkLightFor(EnumSkyBlock.BLOCK, pos)) {
//            IBlockState state = world.getBlockState(pos);
//            world.notifyBlockUpdate(pos, state, state, 3);
//        }
    }

    protected boolean hasClientUpdate() {

        return false;
    }

    public boolean canPlayerChange(PlayerEntity player) {

        return !(this instanceof ISecurable) || ((ISecurable) this).canAccess(player);
    }

    public boolean playerWithinDistance(PlayerEntity player, double distanceSq) {

        return pos.distanceSq(player.getPositionVec(), true) <= distanceSq;
    }

    public boolean isUsableByPlayer(PlayerEntity player) {

        return playerWithinDistance(player, 64D) && world.getTileEntity(pos) == this;
    }

    public boolean onWrench(PlayerEntity player, Direction side) {

        return false;
    }
    // endregion

    // region ITileCallback
    @Override
    public Block block() {

        return getBlockState().getBlock();
    }

    @Override
    public BlockState state() {

        return getBlockState();
    }

    @Override
    public BlockPos pos() {

        return pos;
    }

    @Override
    public World world() {

        return world;
    }
    // endregion
}
