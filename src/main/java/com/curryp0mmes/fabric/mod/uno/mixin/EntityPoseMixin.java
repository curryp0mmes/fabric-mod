package com.curryp0mmes.fabric.mod.uno.mixin;


import com.curryp0mmes.fabric.mod.uno.ClientEnv;
import com.curryp0mmes.fabric.mod.uno.customstuff.PlayerLayDown;
import com.curryp0mmes.fabric.mod.uno.customstuff.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class EntityPoseMixin{


    boolean enabled = false;
    EntityPose currentPose = EntityPose.STANDING;

    public void changePose() {
        if(currentPose == EntityPose.STANDING) currentPose = EntityPose.CROUCHING;
        else if(currentPose == EntityPose.CROUCHING) currentPose = EntityPose.SWIMMING;
        else currentPose = EntityPose.STANDING;
    }

    public void shouldChange() {
        if(PlayerLayDown.changePosition) {
            changePose();
            PlayerLayDown.changePosition = false;
        }

        //if(ClientEnv.)
    }

    @ModifyVariable(method = "updateSize", ordinal = 1, at = @At(value = "STORE"))
    private EntityPose entityPose9(EntityPose pose){
        shouldChange();
        if(enabled) {
            pose = currentPose;
        }
        else currentPose = EntityPose.STANDING;

        return pose;
    }

}
