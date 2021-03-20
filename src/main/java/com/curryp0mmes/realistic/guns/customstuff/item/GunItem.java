package com.curryp0mmes.realistic.guns.customstuff.item;

import com.curryp0mmes.realistic.guns.ModNetworkingConstants;
import com.curryp0mmes.realistic.guns.customstuff.projectile.GunProjectile;
import com.curryp0mmes.realistic.guns.registry.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
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

import java.util.List;

public class GunItem extends Item {



    public GunItem(Settings settings) {
        super(settings);
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.RELOAD_PACKET_ID, (server, client, handler, buf, responseSender) -> {


            ItemStack item = client.getMainHandStack();

            if(item.getItem() != this) return;

            if(!client.abilities.creativeMode) {
                CompoundTag tag = item.getOrCreateTag();
                int amount = tag.getInt("ammunition");
                while(amount < 0 && client.inventory.count(ModItems.AMMO) > 0) {
                    client.inventory.getStack(client.inventory.getSlotWithStack(ModItems.AMMO.getDefaultStack())).decrement(1);
                    amount++;
                }
                tag.putInt("ammunition", amount);
                item.setTag(tag);

                client.sendMessage(new TranslatableText("item.realistic_guns.pistol.ammo_left").append(String.valueOf(amount + this.getMaxAmmunition())), true);
            }
            reloadAnimationCallback(client,item);
        });
    }

    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("ammunition", 0);
        stack.setTag(tag);
        appendTooltip(stack, new TranslatableText("item.realistic_guns.pistol.ammo_left").append(String.valueOf(this.getMaxAmmunition())),TooltipContext.Default.NORMAL);
        return stack;
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, Text str, TooltipContext context) {
        List<Text> tooltip = stack.getTooltip(MinecraftClient.getInstance().player, context);
        tooltip.add(str);
    }

    public int getMaxAmmunition() {
        return 15;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand
        if(!canShoot(world, user, hand)) return TypedActionResult.pass(itemStack);

        CompoundTag tag = itemStack.getOrCreateTag();
        int ammo = tag.getInt("ammunition");

        if (ammo <= this.getMaxAmmunition() * -1 && !user.abilities.creativeMode) {
            user.playSound(SoundEvents.UI_BUTTON_CLICK, 1F, 1F);
            user.sendMessage(new TranslatableText("item.realistic_guns.pistol.no_ammo"), true);
            return TypedActionResult.fail(itemStack);
        }
        if (!user.abilities.creativeMode) {
            ammo--;
            tag.putInt("ammunition", ammo);
            itemStack.setTag(tag);
            user.sendMessage(new TranslatableText("item.realistic_guns.pistol.ammo_left").append(String.valueOf(ammo + this.getMaxAmmunition())), true);
        }


    /*
    user.getItemCooldownManager().set(this, 5);
    Optionally, you can add a cooldown to your item's right-click use, similar to Ender Pearls.
    */
        if (!world.isClient) {
            GunProjectile gunProjectile = new GunProjectile(world, user);
            gunProjectile.setItem(ModItems.BULLET.getDefaultStack());
            gunProjectile.setProperties(user, user.pitch, user.yaw, 0.0F, 5.5F, 0F);
            world.spawnEntity(gunProjectile); // spawns entity
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));


        return fireAnimationCallback(world,user,hand);

    }

    public boolean canShoot(World world, PlayerEntity user, Hand hand) {
        return true;
    }

    public void reloadAnimationCallback(PlayerEntity client, ItemStack item) {
    }

    public TypedActionResult<ItemStack> fireAnimationCallback(World world, PlayerEntity user, Hand hand) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 2F); // plays a globalSoundEvent
        user.getItemCooldownManager().set(this, 10); //cooldown to use it again
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }
}
