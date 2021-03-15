package com.curryp0mmes.fabric.mod.uno.customstuff;

import com.curryp0mmes.fabric.mod.uno.FabricMod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class M4CarbineModel extends AnimatedGeoModel<M4CarbineItem>
{
    @Override
    public Identifier getModelLocation(M4CarbineItem object)
    {
        return new Identifier(FabricMod.MOD_ID, "geo/m4_carbine.geo.json");
    }

    @Override
    public Identifier getTextureLocation(M4CarbineItem object)
    {
        return new Identifier(FabricMod.MOD_ID, "textures/items/rifle_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(M4CarbineItem object)
    {
        return new Identifier(FabricMod.MOD_ID, "animations/m4_carbine.animation.json");
    }
}