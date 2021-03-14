package com.curryp0mmes.fabric.mod.uno;

import com.curryp0mmes.fabric.mod.uno.customstuff.GunItem;
import com.curryp0mmes.fabric.mod.uno.registry.ModItems;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;

@Environment(EnvType.SERVER)
public class ServerEnv implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("Moin meister");


    }
}
