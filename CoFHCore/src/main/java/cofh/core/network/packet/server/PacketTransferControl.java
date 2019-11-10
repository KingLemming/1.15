package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.util.control.ITransferControllableTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.PACKET_TRANSFER_CONTROL;

public class PacketTransferControl extends PacketBase implements IPacketServer {

    protected BlockPos pos;
    protected boolean transferIn;
    protected boolean transferOut;

    public PacketTransferControl() {

        super(PACKET_TRANSFER_CONTROL, CoFHCore.packetHandler);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        World world = player.world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof ITransferControllableTile) {
            ((ITransferControllableTile) tile).setControl(transferIn, transferOut);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBlockPos(pos);
        buf.writeBoolean(transferIn);
        buf.writeBoolean(transferOut);
    }

    @Override
    public void read(PacketBuffer buf) {

        pos = buf.readBlockPos();
        transferIn = buf.readBoolean();
        transferOut = buf.readBoolean();
    }

    public static void sendToServer(ITransferControllableTile tile) {

        PacketTransferControl packet = new PacketTransferControl();
        packet.pos = tile.pos();
        packet.transferIn = tile.transferControl().getTransferIn();
        packet.transferOut = tile.transferControl().getTransferOut();
        packet.sendToServer();
    }

}
