package cofh.core.util;

import cofh.core.CoFHCore;
import cofh.core.network.packet.client.PacketIndexedChat;
import cofh.lib.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class ChatHelper {

    public static final int TEMP_INDEX_SERVER = -661464083; // Random Integer
    public static final int TEMP_INDEX_CLIENT = -1245781222; // Random Integer

    private static boolean indexChatMessages = true;

    public static void sendIndexedChatMessageToPlayer(PlayerEntity player, ITextComponent message) {

        if (player.world == null || Utils.isFakePlayer(player)) {
            return;
        }
        if (indexChatMessages) {
            if (Utils.isServerWorld(player.world)) {
                if (player instanceof ServerPlayerEntity) {
                    PacketIndexedChat.sendToClient(message, TEMP_INDEX_SERVER, (ServerPlayerEntity) player);
                }
            } else {
                CoFHCore.proxy.addIndexedChatMessage(message, TEMP_INDEX_CLIENT);
            }
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendIndexedChatMessagesToPlayer(PlayerEntity player, List<ITextComponent> messages) {

        if (player.world == null || Utils.isFakePlayer(player)) {
            return;
        }
        if (indexChatMessages) {
            for (int i = 0; i < messages.size(); ++i) {
                if (Utils.isServerWorld(player.world)) {
                    if (player instanceof ServerPlayerEntity) {
                        PacketIndexedChat.sendToClient(messages.get(i), TEMP_INDEX_SERVER + i, (ServerPlayerEntity) player);
                    }
                } else {
                    CoFHCore.proxy.addIndexedChatMessage(messages.get(i), TEMP_INDEX_CLIENT + i);
                }
            }
        } else {
            for (ITextComponent message : messages) {
                player.sendMessage(message);
            }
        }
    }

}
