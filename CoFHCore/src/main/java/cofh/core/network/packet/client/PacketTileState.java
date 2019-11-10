package cofh.core.network.packet.client;

import cofh.core.CoFHCore;
import cofh.lib.block.TileCoFH;
import cofh.lib.network.packet.IPacketClient;
import cofh.lib.network.packet.PacketBase;
import io.netty.buffer.Unpooled;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.lib.util.constants.Constants.NETWORK_UPDATE_DISTANCE;
import static cofh.lib.util.constants.Constants.PACKET_STATE;

public class PacketTileState extends PacketBase implements IPacketClient {

    protected BlockPos pos;
    protected PacketBuffer buffer;

    public PacketTileState() {

        super(PACKET_STATE, CoFHCore.packetHandler);
    }

    @Override
    public void handleClient() {

        World world = Minecraft.getInstance().world;
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileCoFH) {
            ((TileCoFH) tile).handleStatePacket(buffer);
            BlockState state = tile.getWorld().getBlockState(pos);
            tile.getWorld().notifyBlockUpdate(pos, state, state, 3);
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

        PacketTileState packet = new PacketTileState();
        packet.pos = tile.pos();
        packet.buffer = tile.getStatePacket(new PacketBuffer(Unpooled.buffer()));
        packet.sendToAllAround(packet.pos, NETWORK_UPDATE_DISTANCE, tile.world().getDimension().getType());
    }

}
