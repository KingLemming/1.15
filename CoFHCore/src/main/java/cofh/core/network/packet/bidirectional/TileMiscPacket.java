package cofh.core.network.packet.bidirectional;

import cofh.core.CoFHCore;
import cofh.core.util.ProxyUtils;
import cofh.lib.network.packet.IPacketClient;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.tileentity.TileCoFH;
import cofh.lib.util.Utils;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.NETWORK_UPDATE_DISTANCE;
import static cofh.lib.util.constants.Constants.PACKET_MISC;

public class TileMiscPacket extends PacketBase implements IPacketClient, IPacketServer {

    protected BlockPos pos;
    protected PacketBuffer buffer;

    public TileMiscPacket() {

        super(PACKET_MISC, CoFHCore.PACKET_HANDLER);
    }

    @Override
    public void handleClient() {

        World world = ProxyUtils.getClientWorld();
        if (world == null) {
            CoFHCore.LOG.error("Client world is null! (Is this being called on the server?)");
            return;
        }
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileCoFH) {
            ((TileCoFH) tile).handleMiscPacket(buffer);
        }
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        World world = player.world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileCoFH) {
            ((TileCoFH) tile).handleMiscPacket(buffer);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBlockPos(pos);
        buf.writeBytes(buffer);
    }

    @Override
    public void read(PacketBuffer buf) {

        buffer = buf;
        pos = buffer.readBlockPos();
    }

    public static void sendToClient(TileCoFH tile) {

        if (Utils.isClientWorld(tile.world())) {
            return;
        }
        TileMiscPacket packet = new TileMiscPacket();
        packet.pos = tile.pos();
        packet.buffer = tile.getMiscPacket(new PacketBuffer(Unpooled.buffer()));
        packet.sendToAllAround(packet.pos, NETWORK_UPDATE_DISTANCE, tile.world().getDimension().getType());
    }

    public static boolean sendToServer(TileCoFH tile) {

        if (tile == null) {
            return false;
        }
        TileMiscPacket packet = new TileMiscPacket();
        packet.pos = tile.pos();
        packet.buffer = tile.getMiscPacket(new PacketBuffer(Unpooled.buffer()));
        packet.sendToServer();
        return true;
    }

}
