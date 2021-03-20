package com.curryp0mmes.realistic.guns.customstuff;

import com.curryp0mmes.realistic.guns.ModNetworkingConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityPose;

public class PlayerLayDown {

    public static EntityPose currentPose = EntityPose.STANDING;

    public static void changePose() {
        if(currentPose == EntityPose.STANDING) currentPose = EntityPose.SWIMMING;
        //else if(currentPose == EntityPose.CROUCHING) currentPose = EntityPose.SWIMMING;
        else currentPose = EntityPose.STANDING;
    }

    public static void registerEvents() {
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.SNEAKING_PACKET_ID, (server, client, handler, buf, responseSender) -> {
            changePose();
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            checkSprinting();
        });
    }
    @Environment(EnvType.CLIENT)
    private static void checkSprinting() {
        if(MinecraftClient.getInstance().player == null) return;
        if(MinecraftClient.getInstance().options.keySprint.isPressed())
            currentPose = EntityPose.STANDING;
    }
}
