package com.curryp0mmes.realistic.guns;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class ServerEnv implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("GunMod Init");
    }
}
