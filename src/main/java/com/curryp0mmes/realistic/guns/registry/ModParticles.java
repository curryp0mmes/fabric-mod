package com.curryp0mmes.realistic.guns.registry;

import com.curryp0mmes.realistic.guns.RealisticGunsMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static final ParticleType GUNFIRE_PARTICLE = FabricParticleTypes.simple(false);

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(RealisticGunsMod.MOD_ID,"gunfire_particle"), GUNFIRE_PARTICLE);
    }
}
