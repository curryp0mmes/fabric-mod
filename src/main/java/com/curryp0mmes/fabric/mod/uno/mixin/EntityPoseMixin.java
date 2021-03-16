package com.curryp0mmes.fabric.mod.uno.mixin;



import com.curryp0mmes.fabric.mod.uno.customstuff.PlayerLayDown;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class EntityPoseMixin{


    boolean enabled = true;
    EntityPose currentPose = EntityPose.STANDING;

    public void changePose() {
        if(currentPose == EntityPose.STANDING) currentPose = EntityPose.CROUCHING;
        else if(currentPose == EntityPose.CROUCHING) currentPose = EntityPose.SWIMMING;
        else currentPose = EntityPose.STANDING;
    }

    public void shouldChange() {
        if(PlayerLayDown.keyDown) {
            changePose();
            PlayerLayDown.keyDown = false;
        }

        //if(ClientEnv.)
    }

    @ModifyVariable(method = "updateSize", ordinal = 0, at = @At(value = "STORE"))
    private EntityPose entityPose9(EntityPose pose){
        shouldChange();
        if(enabled) {
            pose = currentPose;
        }
        //else currentPose = EntityPose.STANDING;

        return pose;
    }

}
