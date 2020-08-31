package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.core.network.packet.IPacketServer;
import cofh.core.network.packet.PacketBase;
import cofh.core.util.control.IRedstoneControllable.ControlMode;
import cofh.core.util.control.IRedstoneControllableTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.core.util.constants.Constants.PACKET_REDSTONE_CONTROL;

public class RedstoneControlPacket extends PacketBase implements IPacketServer {

    protected BlockPos pos;
    protected int threshold;
    protected byte mode;

    public RedstoneControlPacket() {

        super(PACKET_REDSTONE_CONTROL, CoFHCore.PACKET_HANDLER);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        World world = player.world;
        if (!world.isBlockPresent(pos)) {
            return;
        }
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IRedstoneControllableTile) {
            ((IRedstoneControllableTile) tile).setControl(threshold, ControlMode.VALUES[mode]);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBlockPos(pos);
        buf.writeInt(threshold);
        buf.writeByte(mode);
    }

    @Override
    public void read(PacketBuffer buf) {

        pos = buf.readBlockPos();
        threshold = buf.readInt();
        mode = buf.readByte();
    }

    public static void sendToServer(IRedstoneControllableTile tile) {

        RedstoneControlPacket packet = new RedstoneControlPacket();
        packet.pos = tile.pos();
        packet.threshold = tile.redstoneControl().getThreshold();
        packet.mode = (byte) tile.redstoneControl().getMode().ordinal();
        packet.sendToServer();
    }

}
