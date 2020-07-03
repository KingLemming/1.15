package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;

import static cofh.lib.util.constants.Constants.PACKET_KEY_MULTIMODE;

public class ModeChangePacket extends PacketBase implements IPacketServer {

    protected boolean decr;

    public ModeChangePacket() {

        super(PACKET_KEY_MULTIMODE, CoFHCore.PACKET_HANDLER);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        if (!ItemHelper.isPlayerHoldingMultiModeItem(player)) {
            return;
        }
        if (decr && ItemHelper.decrHeldMultiModeItemState(player) || ItemHelper.incrHeldMultiModeItemState(player)) {
            ItemHelper.onHeldMultiModeItemChange(player);
        }
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeBoolean(decr);
    }

    @Override
    public void read(PacketBuffer buf) {

        decr = buf.readBoolean();
    }

    public static void incrMode() {

        sendToServer(false);
    }

    public static void decrMode() {

        sendToServer(true);
    }

    private static void sendToServer(boolean decr) {

        ModeChangePacket packet = new ModeChangePacket();
        packet.decr = decr;
        packet.sendToServer();
    }

}
