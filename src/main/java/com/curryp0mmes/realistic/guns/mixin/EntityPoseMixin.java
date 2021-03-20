package com.curryp0mmes.realistic.guns.mixin;



import com.curryp0mmes.realistic.guns.customstuff.PlayerLayDown;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(PlayerEntity.class)
public abstract class EntityPoseMixin{

    @Shadow public abstract Text getDisplayName();

    @ModifyVariable(method = "updateSize", ordinal = 1, at = @At(value = "STORE"))
    private EntityPose entityPose9(EntityPose pose) {

        if(PlayerLayDown.currentPose != EntityPose.STANDING) {
            pose = PlayerLayDown.currentPose;
        }
        return pose;
    }

}
