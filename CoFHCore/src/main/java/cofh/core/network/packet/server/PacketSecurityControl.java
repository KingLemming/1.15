package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.util.control.ISecurable.AccessMode;
import cofh.lib.util.control.ISecurableTile;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.PACKET_SECURITY_CONTROL;

public class PacketSecurityControl extends PacketBase implements IPacketServer {

    protected BlockPos pos;
    protected byte mode;

    public PacketSecurityControl() {

        super(PACKET_SECURITY_CONTROL, CoFHCore.packetHandler);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        World world = player.world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof ISecurableTile) {
            ((ISecurableTile) tile).setAccess(AccessMode.VALUES[mode]);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBlockPos(pos);
        buf.writeByte(mode);
    }

    @Override
    public void read(PacketBuffer buf) {

        pos = buf.readBlockPos();
        mode = buf.readByte();
    }

    public static void sendToServer(ISecurableTile tile) {

        PacketSecurityControl packet = new PacketSecurityControl();
        packet.pos = tile.pos();
        packet.mode = (byte) tile.securityControl().getAccess().ordinal();
        packet.sendToServer();
    }

}
