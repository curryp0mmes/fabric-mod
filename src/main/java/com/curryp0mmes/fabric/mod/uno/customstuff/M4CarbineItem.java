package com.curryp0mmes.fabric.mod.uno.customstuff;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class M4CarbineItem extends GunItem implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);
    private String controllerName = "controller";

    public M4CarbineItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAmmunition() {
        return 30;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {

        event.getController().setAnimation(new AnimationBuilder().addAnimation("m4_carbine_fire", false));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, controllerName, 1, this::predicate));
    }

    @Override
    public void reloadAnimationCallback(PlayerEntity user, ItemStack item) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, item, controllerName);

        if (controller.getAnimationState() == AnimationState.Stopped) {
            user.sendMessage(new LiteralText("Fire!"), true);
            // If you don't do this, the popup animation will only play once because the
            // animation will be cached.
            controller.markNeedsReload();
            // Set the animation to open the jackinthebox which will start playing music and
            // eventually do the actual animation. Also sets it to not loop
            controller.setAnimation(new AnimationBuilder().addAnimation("m4_carbine_reload", false));
        }

        return;
    }

    @Override
    public TypedActionResult<ItemStack> fireAnimationCallback(World world, PlayerEntity user, Hand hand) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, user.getStackInHand(hand), controllerName);

        if (controller.getAnimationState() == AnimationState.Stopped) {
            user.sendMessage(new LiteralText("Fire!"), true);
            // If you don't do this, the popup animation will only play once because the
            // animation will be cached.
            controller.markNeedsReload();
            // Set the animation to open the jackinthebox which will start playing music and
            // eventually do the actual animation. Also sets it to not loop
            controller.setAnimation(new AnimationBuilder().addAnimation("m4_carbine_fire", false));
        }
        user.getItemCooldownManager().set(this, 5); //cooldown to use it again
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

