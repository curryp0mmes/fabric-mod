package com.curryp0mmes.fabric.mod.uno.customstuff;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class M4CarbineModel extends AnimatedGeoModel<M4CarbineItem>
{
    @Override
    public Identifier getModelLocation(M4CarbineItem object)
    {
        return new Identifier(GeckoLib.ModID, "geo/m4_carbine.geo.json");
    }

    @Override
    public Identifier getTextureLocation(M4CarbineItem object)
    {
        return new Identifier(GeckoLib.ModID, "textures/items/rifle_texture");
    }

    @Override
    public Identifier getAnimationFileLocation(M4CarbineItem object)
    {
        return new Identifier(GeckoLib.ModID, "animations/m4_carbine_reload.animation.json");
    }
}