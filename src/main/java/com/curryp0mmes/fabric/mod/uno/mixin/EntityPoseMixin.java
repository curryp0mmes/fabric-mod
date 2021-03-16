package com.curryp0mmes.fabric.mod.uno.mixin;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

    /*
    @Inject(at = @At("TAIL"), method = "updateSize()V")
    private void updateSize(CallbackInfo info) {

    }
    */

    @ModifyVariable(method = "updateSize", at = @At(value = "STORE", ordinal = 0))
    private EntityPose entityPose9(EntityPose entityPose9){
        System.out.println("Person is currently " + entityPose9 + " that means standing = " + (entityPose9==EntityPose.STANDING));
        return entityPose9;
    }

}
