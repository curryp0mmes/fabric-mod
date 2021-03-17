package com.curryp0mmes.fabric.mod.uno.mixin;



import com.curryp0mmes.fabric.mod.uno.customstuff.PlayerLayDown;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class EntityPoseMixin{


    boolean enabled = true;


    @ModifyVariable(method = "updateSize", ordinal = 1, at = @At(value = "STORE"))
    private EntityPose entityPose9(EntityPose pose){


        if(enabled) {
            pose = PlayerLayDown.currentPose;
        }
        //else currentPose = EntityPose.STANDING;

        return pose;
    }

}
