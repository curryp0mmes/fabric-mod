package com.curryp0mmes.realistic.guns.customstuff.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class GunfireParticle extends ParticleType {


    /**
     * @param alwaysShow        whether this particle type should appear regardless of
     * @param parametersFactory
     */
    public GunfireParticle(boolean alwaysShow, ParticleEffect.Factory parametersFactory) {
        super(alwaysShow, parametersFactory);
    }

    @Override
    public Codec getCodec() {
        return null;
    }
}
