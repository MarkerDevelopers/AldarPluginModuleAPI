package com.ndy.util;

import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleManager {

    public static void sendTitle(String message, int seocond, Player reciver) {
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), seocond, 40 ,seocond);
        ((CraftPlayer) reciver).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendActionBar(String message, Player reciver) {
        PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR,
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"));
        ((CraftPlayer) reciver).getHandle().playerConnection.sendPacket(packet);
    }

}
