package cofh.core.util;

import cofh.core.CoFHCore;
import cofh.core.network.packet.client.PacketIndexedChat;
import cofh.lib.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class ChatHelper {

    public static final int tempChatIndexServer = -661464083; // Random Integer
    public static final int tempChatIndexClient = -1245781222; // Random Integer
    public static final boolean indexChat = true;

    //	static {
    //		String category = "gui.chat";
    //		CoFHCore.CONFIG_CLIENT.getCategory(category).setComment("The options in this section change core Minecraft behavior and are not limited to CoFH mods.");
    //
    //		String comment = "Set to false to disable outdated CoFH info chat messages being removed from chat";
    //
    //		indexChat = CoFHCore.CONFIG_CLIENT.get(category, "RemoveOutdatedChat", true, comment);
    //	}

    public static ITextComponent getChatComponent(Object object) {

        if (object instanceof ITextComponent) {
            return (ITextComponent) object;
        } else if (object instanceof String) {
            return new StringTextComponent((String) object);
        } else if (object instanceof ItemStack) {
            return ((ItemStack) object).getTextComponent();
        } else if (object instanceof Entity) {
            return ((Entity) object).getDisplayName();
        } else {
            return new StringTextComponent(String.valueOf(object));
        }
    }

    public static ITextComponent formChatComponent(Object... chats) {

        ITextComponent chat = getChatComponent(chats[0]);
        for (int i = 1, chatsLength = chats.length; i < chatsLength; i++) {
            chat.appendSibling(getChatComponent(chats[i]));
        }

        return chat;
    }

    public static ITextComponent translate(String key) {

        return new TranslationTextComponent(key);
    }

    public static String toJSON(ITextComponent chatComponent) {

        return ITextComponent.Serializer.toJson(chatComponent);
    }

    public static ITextComponent fromJSON(String string) {

        return ITextComponent.Serializer.fromJsonLenient(string);
    }

    public static void sendIndexedChatMessageToPlayer(PlayerEntity player, ITextComponent message) {

        if (player.world == null || Utils.isFakePlayer(player)) {
            return;
        }
        if (indexChat) {
            if (Utils.isServerWorld(player.world)) {
                if (player instanceof ServerPlayerEntity) {
                    PacketIndexedChat.sendToClient(message, tempChatIndexServer, (ServerPlayerEntity) player);
                }
            } else {
                CoFHCore.proxy.addIndexedChatMessage(message, tempChatIndexClient);
            }
        } else {
            player.sendMessage(message);
        }
    }

    public static void sendIndexedChatMessagesToPlayer(PlayerEntity player, List<ITextComponent> messages) {

        if (player.world == null || Utils.isFakePlayer(player)) {
            return;
        }
        if (indexChat) {
            for (int i = 0; i < messages.size(); i++) {
                if (Utils.isServerWorld(player.world)) {
                    if (player instanceof ServerPlayerEntity) {
                        PacketIndexedChat.sendToClient(messages.get(i), tempChatIndexServer + i, (ServerPlayerEntity) player);
                    }
                } else {
                    CoFHCore.proxy.addIndexedChatMessage(messages.get(i), tempChatIndexClient + i);
                }
            }
        } else {
            for (ITextComponent message : messages) {
                player.sendMessage(message);
            }
        }
    }

}
