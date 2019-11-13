package cofh.core.network.packet.server;

import cofh.core.CoFHCore;
import cofh.lib.item.IMultiModeItem;
import cofh.lib.network.packet.IPacketServer;
import cofh.lib.network.packet.PacketBase;
import cofh.lib.util.helpers.ItemHelper;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import static cofh.lib.util.constants.Constants.PACKET_KEY_MULTIMODE;

public class MultiModeItemPacket extends PacketBase implements IPacketServer {

    protected boolean decr;

    public MultiModeItemPacket() {

        super(PACKET_KEY_MULTIMODE, CoFHCore.packetHandler);
    }

    @Override
    public void handleServer(ServerPlayerEntity player) {

        if (!ItemHelper.isPlayerHoldingMultiModeItem(player)) {
            return;
        }
        if (decr && ItemHelper.decrHeldMultiModeItemState(player) || ItemHelper.incrHeldMultiModeItemState(player)) {
            ItemStack heldItem = ItemHelper.getHeldMultiModeStack(player);
            ((IMultiModeItem) heldItem.getItem()).onModeChange(player, heldItem);
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

        MultiModeItemPacket packet = new MultiModeItemPacket();
        packet.decr = decr;
        packet.sendToServer();
    }

}
