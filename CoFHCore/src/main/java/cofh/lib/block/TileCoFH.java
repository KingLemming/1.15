package cofh.lib.block;

import cofh.lib.util.Utils;
import cofh.lib.util.control.ISecurable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileCoFH extends TileEntity implements ITileCallback {

    public TileCoFH(TileEntityType<?> tileEntityTypeIn) {

        super(tileEntityTypeIn);
    }

    @Override
    public void onLoad() {

        super.onLoad();

        if (world != null && Utils.isClientWorld(world) && !hasClientUpdate()) {
            world.tickableTileEntities.remove(this);
        }
        validate();
    }

    // region HELPERS
    protected int getComparatorInputOverride() {

        return 0;
    }

    protected void neighborChanged() {

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

        return playerWithinDistance(player, 64D) && world != null && world.getTileEntity(pos) == this;
    }

    public boolean onWrench(PlayerEntity player, Direction side) {

        return false;
    }
    // endregion

    // region NETWORK
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {

        return new SUpdateTileEntityPacket(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {

        read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {

        return this.write(new CompoundNBT());
    }

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
