package com.curryp0mmes.fabric.mod.uno.customstuff;

import com.curryp0mmes.fabric.mod.uno.FabricMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.controller.AnimationController;

public class AnimationEvent <M4CarbineItem extends IAnimatable>{
    AnimationController.IParticleListener snowstormfire;


    public final String effect;
    public final String locator;

    public AnimationEvent(String effect, String locator, AnimationController controller) {

        this.effect = effect;
        this.locator = locator;
    }


    void registerSoundListener() {

    }
}
