package cofh.core.network.packet.client;

import cofh.core.CoFHCore;
import cofh.core.util.ChatHelper;
import cofh.lib.network.packet.IPacketClient;
import cofh.lib.network.packet.PacketBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;

import static cofh.lib.util.constants.Constants.PACKET_CHAT;

public class PacketIndexedChat extends PacketBase implements IPacketClient {

    protected int index;
    protected String message;

    public PacketIndexedChat() {

        super(PACKET_CHAT, CoFHCore.packetHandler);
    }

    @Override
    public void handleClient() {

        CoFHCore.proxy.addIndexedChatMessage(ChatHelper.fromJSON(message), index);
    }

    @Override
    public void write(PacketBuffer buf) {

        buf.writeInt(index);
        buf.writeString(message);
    }

    @Override
    public void read(PacketBuffer buf) {

        index = buf.readInt();
        message = buf.readString(Short.MAX_VALUE);
    }

    public static void sendToClient(ITextComponent chat, int index, ServerPlayerEntity player) {

        PacketIndexedChat packet = new PacketIndexedChat();
        packet.index = index;
        packet.message = ChatHelper.toJSON(chat);
        packet.sendToPlayer(player);
    }

}
