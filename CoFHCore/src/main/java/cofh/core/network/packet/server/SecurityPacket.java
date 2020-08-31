package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.core.network.packet.IPacketServer;
import cofh.core.network.packet.PacketBase;
import cofh.core.util.control.ISecurable;
import cofh.core.util.control.ISecurable.AccessMode;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

import static cofh.core.util.constants.Constants.PACKET_SECURITY;

public class SecurityPacket extends PacketBase implements IPacketServer {

    protected byte mode;

    public SecurityPacket() {

        super(PACKET_SECURITY, CoFHCore.PACKET_HANDLER);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        if (player.openContainer instanceof ISecurable) {
            ((ISecurable) player.openContainer).setAccess(AccessMode.VALUES[mode]);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeByte(mode);
    }

    @Override
    public void read(PacketBuffer buf) {

        mode = buf.readByte();
    }

    public static void sendToServer(AccessMode accessMode) {

        SecurityPacket packet = new SecurityPacket();
        packet.mode = (byte) accessMode.ordinal();
        packet.sendToServer();
    }

}
