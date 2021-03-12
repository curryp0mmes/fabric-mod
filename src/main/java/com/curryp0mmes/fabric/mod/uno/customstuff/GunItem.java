package com.curryp0mmes.fabric.mod.uno.customstuff;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class GunItem extends Item {
    private int ammunition = 10;
    public GunItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand

        if(this.ammunition == 0 && !user.abilities.creativeMode) {
            user.playSound(SoundEvents.UI_BUTTON_CLICK, 1F,1F);
            user.sendMessage(new TranslatableText("item.curry.pistol.no_ammo"), true);
            return TypedActionResult.fail(itemStack);
        }
        else if(world.isClient && !user.abilities.creativeMode) {
            //itemStack.
            this.ammunition -= 1;
            user.sendMessage(new TranslatableText("item.curry.pistol.ammo_left").append(String.valueOf(this.ammunition)), true);
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 2F); // plays a globalSoundEvent
        user.getItemCooldownManager().set(this, 20); //cooldown to use it again
        /*
		user.getItemCooldownManager().set(this, 5);
		Optionally, you can add a cooldown to your item's right-click use, similar to Ender Pearls.
		*/
        if (!world.isClient) {
            GunProjectile gunProjectile = new GunProjectile(world, user);
            gunProjectile.setItem(itemStack);
            gunProjectile.setProperties(user, user.pitch, user.yaw, 0.0F, 5.5F, 0F);
            world.spawnEntity(gunProjectile); // spawns entity
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));


        return TypedActionResult.success(itemStack, world.isClient());
    }

}
