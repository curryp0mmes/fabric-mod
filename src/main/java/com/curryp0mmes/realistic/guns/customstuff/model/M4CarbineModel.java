package com.curryp0mmes.realistic.guns.customstuff.model;

import com.curryp0mmes.realistic.guns.FabricMod;
import com.curryp0mmes.realistic.guns.customstuff.item.M4CarbineItem;
import net.minecraft.util.Identifier;
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