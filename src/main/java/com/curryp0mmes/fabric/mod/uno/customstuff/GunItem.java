package com.curryp0mmes.fabric.mod.uno.customstuff;

import com.curryp0mmes.fabric.mod.uno.ModNetworkingConstants;
import com.curryp0mmes.fabric.mod.uno.registry.ModItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.awt.*;
import java.util.List;

public class GunItem extends Item implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);

    public GunItem(Settings settings) {
        super(settings);
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.RELOAD_PACKET_ID, (server, client, handler, buf, responseSender) -> {
            ItemStack item = client.getMainHandStack();
            if(item.getTag().contains("ammunition") && item.getItem() instanceof GunItem && !client.abilities.creativeMode) {
                CompoundTag tag = item.getTag();
                int amount = tag.getInt("ammunition");
                while(amount < 0 && client.inventory.count(ModItems.AMMO) > 0) {
                    client.inventory.getStack(client.inventory.getSlotWithStack(ModItems.AMMO.getDefaultStack())).decrement(1);
                    amount++;
                }
                tag.putInt("ammunition", amount);
                item.setTag(tag);
            }

        });
    }


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand

        CompoundTag tag = itemStack.getOrCreateTag();
        int ammo = tag.getInt("ammunition");

        if (ammo == -15 && !user.abilities.creativeMode) {
            user.playSound(SoundEvents.UI_BUTTON_CLICK, 1F, 1F);
            user.sendMessage(new TranslatableText("item.curry.pistol.no_ammo"), true);
            return TypedActionResult.fail(itemStack);
        }
        if (!user.abilities.creativeMode) {
            ammo--;
            tag.putInt("ammunition", ammo);
            itemStack.setTag(tag);
            user.sendMessage(new TranslatableText("item.curry.pistol.ammo_left").append(String.valueOf(ammo+15)), true);
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
    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("Soaryn_chest_popup", true));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
