package com.curryp0mmes.fabric.mod.uno.customstuff;

import com.curryp0mmes.fabric.mod.uno.ModNetworkingConstants;
import com.curryp0mmes.fabric.mod.uno.customstuff.item.GunItem;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerLayDown {

    public static void registerEvents() {
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.SNEAKING_PACKET_ID, (server, client, handler, buf, responseSender) -> {
            //if(!(client.getMainHandStack().getItem() instanceof GunItem) && !(client.getOffHandStack().getItem() instanceof GunItem)) return;
            if(client.abilities.flying) return;

            if(client.getPose() == EntityPose.STANDING) client.setPose(EntityPose.CROUCHING);
            if(client.getPose() == EntityPose.CROUCHING) client.setPose(EntityPose.SWIMMING);
            else client.setPose(EntityPose.STANDING);
        });
    }


}
