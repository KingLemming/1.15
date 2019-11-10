package cofh.lib.block;

import cofh.lib.util.control.ISecurable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
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
    protected int getComparatorInputOverride() {

        return 0;
    }

    protected int getLightValue() {

        return 0;
    }
    // endregion

    // region HELPERS
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

    // region NETWORK
    // TODO: Fix
    //    @Nullable
    //    @Override
    //    public SPacketUpdateTileEntity getUpdatePacket() {
    //
    //        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    //    }
    //
    //    @Override
    //    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
    //
    //        readFromNBT(pkt.getNbtCompound());
    //    }
    //
    //    @Override
    //    public CompoundNBT getUpdateTag() {
    //
    //        return this.writeToNBT(new NBTTagCompound());
    //    }

    public PacketBuffer getControlPacket(PacketBuffer buffer) {

        return buffer;
    }

    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        return buffer;
    }

    public PacketBuffer getStatePacket(PacketBuffer buffer) {

        return buffer;
    }

    public void handleControlPacket(PacketBuffer buffer) {

    }

    public void handleGuiPacket(PacketBuffer buffer) {

    }

    public void handleStatePacket(PacketBuffer buffer) {

    }

    public void setActive(boolean active) {

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
