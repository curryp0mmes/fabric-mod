package com.curryp0mmes.fabric.mod.uno.customstuff;

import com.curryp0mmes.fabric.mod.uno.ModNetworkingConstants;
import com.curryp0mmes.fabric.mod.uno.customstuff.item.GunItem;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerLayDown {

    public static boolean keyDown = false;

    public static void registerEvents() {
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.SNEAKING_PACKET_ID, (server, client, handler, buf, responseSender) -> {
            System.out.println("Key was pressed");
            keyDown = true;
        });
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.NOT_SNEAKING_PACKET_ID, (server, client, handler, buf, responseSender) -> {
            System.out.println("Key was released");
            keyDown = false;
        });
    }


}
